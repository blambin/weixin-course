package com.jiezh.controller.weixin;

import com.jiezh.entity.Course;
import com.jiezh.entity.WeixinOrders;
import com.jiezh.entity.WeixinUser;
import com.jiezh.pub.Env;
import com.jiezh.pub.web.WebAction;
import com.jiezh.pub.weixin.sdk.WXPayConstants;
import com.jiezh.pub.weixin.sdk.WXPayUtil;
import com.jiezh.service.weixin.CourseService;
import com.jiezh.service.weixin.WeixinOrdersService;
import com.jiezh.service.weixin.WeixinUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("/weixin/pay")
public class WeixinPayController extends WebAction {

    @Autowired
    WeixinOrdersService weixinOrdersService;

    @Autowired
    WeixinUserService weixinUserService;

    @Autowired
    CourseService courseService;

    /**
     * 该链接是通过【统一下单API】中提交的参数notify_url设置，如果链接无法访问，商户将无法接收到微信通知。
     * 通知url必须为直接可访问的url，不能携带参数。示例：notify_url：“https://pay.weixin.qq.com/wxpay/pay.action”
     * <p>
     * 支付完成后，微信会把相关支付结果和用户信息发送给商户，商户需要接收处理，并返回应答。
     * 对后台通知交互时，如果微信收到商户的应答不是成功或超时，微信认为通知失败，微信会通过一定的策略定期重新发起通知，尽可能提高通知的成功率，但微信不保证通知最终能成功。
     * （通知频率为15/15/30/180/1800/1800/1800/1800/3600，单位：秒）
     * 注意：同样的通知可能会多次发送给商户系统。商户系统必须能够正确处理重复的通知。
     * 推荐的做法是，当收到通知进行处理时，首先检查对应业务数据的状态，判断该通知是否已经处理过，如果没有处理过再进行处理，如果处理过直接返回结果成功。在对业务数据进行状态检查和处理之前，要采用数据锁进行并发控制，以避免函数重入造成的数据混乱。
     * 特别提醒：商户系统对于支付结果通知的内容一定要做签名验证，防止数据泄漏导致出现“假通知”，造成资金损失。
     */
    @RequestMapping("/wxnotify")
    @ResponseBody
    public String wxnotify() throws Exception {


        String resXml = "";

        InputStream inStream;
        try {

            inStream = request.getInputStream();


            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }

            WXPayUtil.getLogger().error("微信支付----付款成功----");

            outSteam.close();
            inStream.close();

            // 获取微信调用我们notify_url的返回信息
            String result = new String(outSteam.toByteArray(), "utf-8");
            WXPayUtil.getLogger().error("微信支付----result----=" + result);

            // xml转换为map
            Map<String, String> map = WXPayUtil.xmlToMap(result);
            if (Env.SUCCESS.equalsIgnoreCase(map.get("result_code"))) {

                WXPayUtil.getLogger().error("微信支付----返回成功");

                if (WXPayUtil.isSignatureValid(map, WXPayConstants.API_KEY)) {

                    // 订单处理 操作 orderconroller 的回写操作?
                    WXPayUtil.getLogger().error("微信支付----验证签名成功");

                    /*backxml.put("return_code", "<![CDATA[SUCCESS]]>");
                    backxml.put("return_msg", "<![CDATA[OK]]>");
                    // 告诉微信服务器，我收到信息了，不要在调用回调action了
                    response.getWriter().write(WXPayUtil.mapToXml(backxml));
                    WXPayUtil.getLogger().error("微信支付 ~~~~~~~~~~~~~~~~执行完毕？backxml=");*/

                    // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                            + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";


                    // 处理业务 -修改订单支付状态
                    WXPayUtil.getLogger().error("微信支付回调：修改的订单=" + map.get("out_trade_no"));

                    // 修改订单
                    WeixinOrders orders = new WeixinOrders();
                    orders.setOutTradeNo(map.get("out_trade_no"));
                    // 订单状态：1：预支付；2：支付中；3：已付款；4：完结
                    orders.setStatus(Env.WEIXIN_ORDERS_STATUS_3);
                    int updateResult = weixinOrdersService.modifyWeixinOrdersByNo(orders);
                    if (updateResult > 0) {

                        // 如果是购买会员，应该修改用户信息
                        orders = weixinOrdersService.queryWeixinOrdersByNo(orders.getOutTradeNo());
                        if (orders != null) {
                            Course course = courseService.queryCourseById(orders.getCourseId());
                            if (Env.WEIXIN_COURSE_TYPE_4.equals(course.getType())) {
                                // 如果是会员类型，修改用户会员标识
                                WeixinUser weixinUser = weixinUserService.queryWeixinUserById(orders.getUserId());
                                weixinUser.setIsVip(Env.WEIXIN_USER_IS_VIP_1);
                                weixinUserService.modifyWeixinUserById(weixinUser);
                                request.getSession().setAttribute("weixinUser", weixinUser);
                            }

                        }

                        WXPayUtil.getLogger().error("微信支付回调：修改订单支付状态成功");
                    } else {
                        WXPayUtil.getLogger().error("微信支付回调：修改订单支付状态失败");
                    }

                }

            } else {
                WXPayUtil.getLogger().info("支付失败,错误信息：" + map.get("err_code"));
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }

        } catch (IOException e) {
            e.printStackTrace();
            WXPayUtil.getLogger().error("支付回调发布异常：" + e);
        } finally {
            // 处理业务完毕
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        }

        return resXml;
    }

}
