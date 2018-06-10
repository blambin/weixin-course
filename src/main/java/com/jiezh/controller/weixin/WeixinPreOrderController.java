package com.jiezh.controller.weixin;

import com.jiezh.entity.Course;
import com.jiezh.entity.WeixinOrders;
import com.jiezh.entity.WeixinUser;
import com.jiezh.pub.Env;
import com.jiezh.pub.util.DateUtil;
import com.jiezh.pub.web.WebAction;
import com.jiezh.pub.weixin.sdk.WXPay;
import com.jiezh.pub.weixin.sdk.WXPayConfigImpl;
import com.jiezh.pub.weixin.sdk.WXPayConstants;
import com.jiezh.pub.weixin.sdk.WXPayUtil;
import com.jiezh.service.weixin.CourseService;
import com.jiezh.service.weixin.UserPromoterLogService;
import com.jiezh.service.weixin.WeixinOrdersService;
import com.jiezh.service.weixin.WeixinUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("/weixin/order")
public class WeixinPreOrderController extends WebAction {

    @Autowired
    WeixinOrdersService weixinOrdersService;

    @Autowired
    CourseService courseService;

    @Autowired
    WeixinUserService weixinUserService;

    @Autowired
    UserPromoterLogService userPromoterLogService;

    @RequestMapping("/sendRedPack")
    @ResponseBody
    public Map<String, Object> sendRedPack() {

        try {

            // 当前微信授权登陆用户
            WeixinUser user = (WeixinUser) request.getSession().getAttribute("weixinUser");
            // 提现金额
            String defaultMoney = String.valueOf(user.getPromoterMoney());

            // 微信支付对象
            WXPay wxPay = new WXPay(WXPayConfigImpl.getInstance());

            // 商户订单号（每个订单号必须唯一）组成：mch_id+yyyymmdd+10位一天内不能重复的数字
            String yyyyMMddHHmmss = DateUtil.date2String(new Date(), "yyyyMMddHHmmss");
            int str4 = (int) (Math.random() * 10000);
            String mch_billno = WXPayConstants.MCH_ID + yyyyMMddHHmmss + str4;

            System.out.println("mch_billno-" + mch_billno);

            // 构造请求参数数据
            HashMap<String, String> data = new HashMap<String, String>();
            // 商户订单号（每个订单号必须唯一。取值范围：0~9，a~z，A~Z）接口根据商户订单号支持重入，如出现超时可再调用。
            data.put("mch_billno", mch_billno);
            // 商户名称：红包发送者名称
            data.put("send_name", "港生投资");
            // 接受红包的用户openid
            data.put("re_openid", user.getOpenid());
            // 付款金额，单位分，系统是元，所以需要*100
            Double feeStr = Double.valueOf(defaultMoney) * 100;
            data.put("total_amount", String.valueOf(feeStr.intValue()));
            // 红包发放总人数
            data.put("total_num", "1");
            // 红包祝福语
            data.put("wishing", "恭喜你，成功提现现金红包");
            // 调用接口的机器Ip地址
            data.put("client_ip", request.getRemoteAddr());
            // 活动名称
            data.put("act_name", "红包提现");
            // 备注信息
            data.put("remark", "发起" + defaultMoney + "元红包提现");

            // 商户平台-现金红包-发放普通红包
            Map<String, String> resultMap = wxPay.sendRedPack(data);

            System.out.println("wxPay.sendRedPack:" + resultMap);

            // 取得微信返回结果
            if (Env.SUCCESS.equals(resultMap.get("return_code"))) {

                // 需要扣减的提现金额
                BigDecimal subMoney = new BigDecimal(defaultMoney);

                // 扣减用户提现金额
                BigDecimal promoterMoney = user.getPromoterMoney().subtract(subMoney);
                weixinUserService.modifyWeixinUserPromoterMoneyById(user.getId(), promoterMoney);

                // 插入金额变动记录
                userPromoterLogService.insertUserPromoterLog(null, user.getId(), subMoney.multiply(new BigDecimal(-1)));

                return success("ok", resultMap);

            } else {
                // 失败
                return fail("error", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fail("error", null);
    }

    /**
     * 微信预支付
     */
    @RequestMapping("/preOrder")
    @ResponseBody
    public Map<String, Object> preOrder() {

        // 商户订单号
        String out_trade_no = WXPayUtil.generateUUID();
        // 终端IP
        String spbill_create_ip = request.getRemoteAddr();
        // 商品描述
        String body = "秦晋科技-课程";
        // 微信统一下单接口请求地址
        Map<String, String> resultMap = null;
        // 支付二次生成签名
        Map<String, String> chooseWXPayMap = null;

        try {

            WXPay wxPay = new WXPay(WXPayConfigImpl.getInstance());
            Course course = (Course) getBeanByFix(Course.class, "c", "");
            course = courseService.queryCourseById(course.getId());

            if (course == null) {
                return fail("error", null);
            }

            // 当前微信授权登陆用户
            WeixinUser user = (WeixinUser) request.getSession().getAttribute("weixinUser");
            WeixinOrders orders = weixinOrdersService.queryOrderByIdAndUserId(course.getId(), user.getId(), Env.WEIXIN_ORDERS_STATUS_1);

            // 免费
            if (Env.WEIXIN_COURSE_IS_FREE_1.equals(course.getIsFree())) {

                // 新增order订单
                modifyOrders(out_trade_no, spbill_create_ip, Env.WEIXIN_ORDERS_STATUS_3, course, user, null, null);

                return success("ok", null);
            } else {

                // 收费

                if (orders != null) {

                    chooseWXPayMap = chooseWXPayMap(out_trade_no, spbill_create_ip, null, course, user, orders);

                    return success("ok", chooseWXPayMap);

                } else {

                    resultMap = unifiedOrder(out_trade_no, spbill_create_ip, body, wxPay, String.valueOf(course.getPrice()), user);

                    // 取得微信返回结果
                    if (Env.SUCCESS.equals(resultMap.get("return_code"))) {

                        chooseWXPayMap = chooseWXPayMap(out_trade_no, spbill_create_ip, resultMap, course, user, null);

                        return success("ok", chooseWXPayMap);

                    } else {
                        // 失败
                        return fail("error", null);
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return fail("error", null);
    }

    private Map<String, String> chooseWXPayMap(String out_trade_no, String spbill_create_ip, Map<String, String> resultMap, Course course, WeixinUser user, WeixinOrders orders) throws Exception {

        String prepay_id;
        String nonce_str;

        // 为空时说明时修改
        if (resultMap != null) {
            prepay_id = resultMap.get("prepay_id");
            nonce_str = resultMap.get("nonce_str");

            modifyOrders(out_trade_no, spbill_create_ip, Env.WEIXIN_ORDERS_STATUS_1, course, user, prepay_id, nonce_str);


        } else {
            prepay_id = orders.getPrepayId();
            nonce_str = orders.getNonceStr();
        }

        // 支付二次生成签名
        Map<String, String> chooseWXPayMap = new HashMap<>();
        chooseWXPayMap.put("appId", WXPayConstants.APP_ID);
        chooseWXPayMap.put("timeStamp", String.valueOf(WXPayUtil.getCurrentTimestamp()));
        chooseWXPayMap.put("nonceStr", nonce_str);
        chooseWXPayMap.put("package", "prepay_id=" + prepay_id);
        chooseWXPayMap.put("signType", WXPayConstants.MD5);


        System.out.println("wxPay.chooseWXPayMap:" + chooseWXPayMap.toString());

        String paySign = WXPayUtil.generateSignature(chooseWXPayMap, WXPayConstants.API_KEY);
        chooseWXPayMap.put("paySign", paySign);

        System.out.println("wxPay.paySign:" + paySign);

        return chooseWXPayMap;
    }

    private void modifyOrders(String out_trade_no, String spbill_create_ip, String status, Course course, WeixinUser user, String prepay_id, String nonce_str) {
        // 新增order订单
        WeixinOrders orders = new WeixinOrders();
        orders.setPrepayId(prepay_id);
        orders.setStatus(status);
        orders.setSpbillCreateIp(spbill_create_ip);
        orders.setTotalFee(course.getPrice());
        orders.setBody(course.getIntroduce());
        orders.setCourseName(course.getName());
        orders.setOutTradeNo(out_trade_no);
        orders.setUserId(user.getId());
        orders.setNonceStr(nonce_str);
        orders.setCourseId(course.getId());

        // 新增订单
        weixinOrdersService.modifyWeixinOrders(orders);
    }

    private Map<String, String> unifiedOrder(String out_trade_no, String spbill_create_ip, String body, WXPay wxPay, String total_fee, WeixinUser user) throws Exception {
        Map<String, String> resultMap;// 构造请求参数数据
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("body", body);
        data.put("out_trade_no", out_trade_no);
        data.put("device_info", "WEB");
        data.put("fee_type", "CNY");
        // 默认单位为分，系统是元，所以需要*100
        Double feeStr = Double.valueOf(total_fee) * 100;
        data.put("total_fee", String.valueOf(feeStr.intValue()));
        data.put("spbill_create_ip", spbill_create_ip);
        data.put("notify_url", WXPayConstants.NOTIFY_URL);
        data.put("trade_type", WXPayConstants.TRADE_TYPE);
        // trade_type=JSAPI时（即公众号支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。
        data.put("openid", user.getOpenid());
        // data.put("time_expire", "20170112104120");

        // 微信统一下单接口请求地址
        resultMap = wxPay.unifiedOrder(data);
        System.out.println("wxPay.unifiedOrder:" + resultMap);

        return resultMap;
    }


}
