package com.jiezh.controller.weixin;

import com.jiezh.entity.WeixinUser;
import com.jiezh.pub.Env;
import com.jiezh.pub.web.WebAction;
import com.jiezh.pub.weixin.sdk.WXPayConstants;
import com.jiezh.pub.weixin.sdk.WXPayUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("/weixin/test")
public class TestWeixinPreOrderController extends WebAction {

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
        // 标价金额
        String total_fee = "1";

        try {

            // 当前微信授权登陆用户
            WeixinUser user = new WeixinUser();
            user.setOpenid("oBi_S07R6K3kyCMpdNcDY3zEU7hk");
            user.setId(3);

            // 构造请求参数数据
            HashMap<String, String> data = new HashMap<String, String>();
            data.put("body", body);
            data.put("out_trade_no", out_trade_no);
            data.put("device_info", "WEB");
            data.put("fee_type", "CNY");
            data.put("total_fee", total_fee);
            data.put("spbill_create_ip", spbill_create_ip);
            data.put("notify_url", WXPayConstants.NOTIFY_URL);
            data.put("trade_type", WXPayConstants.TRADE_TYPE);
            // trade_type=JSAPI时（即公众号支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。
            data.put("openid", user.getOpenid());
            // data.put("time_expire", "20170112104120");

            // 微信统一下单接口请求地址
            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("nonce_str", "fkcKvM9hjPzUxCQs");
            resultMap.put("device_info", "WEB");
            resultMap.put("appid", "wx7ee19649ab15e945");
            resultMap.put("sign", "31668661E68290317BA90FF1B6D91431");
            resultMap.put("trade_type", "JSAPI");
            resultMap.put("return_msg", "OK");
            resultMap.put("result_code", "SUCCESS");
            resultMap.put("mch_id", "1502656161");
            resultMap.put("return_code", "SUCCESS");
            resultMap.put("prepay_id", "wx09224657664814dc57abe8361376151339");

            System.out.println("wxPay.unifiedOrder:" + resultMap);

            // 取得微信返回结果
            if (Env.SUCCESS.equals(resultMap.get("return_code"))) {

                //
                /*WeixinOrders orders = (WeixinOrders) getBean(WeixinOrders.class);

                //Course course = courseService.queryCourseById(orders.getCourseId());
                Course course = courseService.queryCourseById(1);

                orders.setPrepayId(resultMap.get("prepay_id"));
                orders.setStatus(Env.WEIXIN_ORDERS_STATUS_1);
                orders.setOutTradeNo(String.valueOf(System.currentTimeMillis()));
                orders.setSpbillCreateIp(spbill_create_ip);
                orders.setTotalFee(course.getPrice());
                orders.setBody(course.getName());
                orders.setCourseName(course.getName());
                orders.setOutTradeNo(out_trade_no);
                orders.setUserId(user.getId());

                // 新增订单
                weixinOrdersService.modifyWeixinOrders(orders);*/


                Map<String, String> chooseWXPayMap = new HashMap<>();
                chooseWXPayMap.put("appId", WXPayConstants.APP_ID);
                chooseWXPayMap.put("timeStamp", String.valueOf(WXPayUtil.getCurrentTimestamp()));
                chooseWXPayMap.put("nonceStr", resultMap.get("nonce_str"));
                chooseWXPayMap.put("package", "prepay_id=" + resultMap.get("prepay_id"));
                chooseWXPayMap.put("signType", WXPayConstants.MD5);


                System.out.println("wxPay.chooseWXPayMap:" + chooseWXPayMap.toString());

                String paySign = WXPayUtil.generateSignature(chooseWXPayMap, WXPayConstants.API_KEY);
                chooseWXPayMap.put("paySign", paySign);

                System.out.println("wxPay.paySign:" + paySign);

                return success("ok", chooseWXPayMap);
            } else {
                // 失败

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return fail("error", null);
    }


}
