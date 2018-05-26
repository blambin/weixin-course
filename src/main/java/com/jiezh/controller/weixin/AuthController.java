package com.jiezh.controller.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiezh.entity.WeixinUser;
import com.jiezh.pub.util.Http;
import com.jiezh.pub.web.WebAction;
import com.jiezh.pub.weixin.sdk.WXPayConstants;
import com.jiezh.service.weixin.WeixinUserService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * 用户控制类
 *
 * @author yclimb
 * @date 2018/4/30
 */
@Controller
@Scope("prototype")
@RequestMapping("/weixin/auth")
public class AuthController extends WebAction {

    @Autowired
    private WeixinUserService weixinUserService;

    /**
     * 进入用户列表
     *
     * @return
     */
    @RequestMapping("/authorize")
    public String authorize() throws IOException {

        //获取access_token
        String json1 = "";
        //获取用户信息
        String json2 = "";
        //跳转页面标识
        String state = request.getParameter("state");
        //通过code获取access_token
        String code = request.getParameter("code");
        String httpurl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String param = "appid=" + WXPayConstants.APP_ID + "&secret=" + WXPayConstants.SECRET + "&code=" + code + "&grant_type=authorization_code";

        //网页授权获取用户信息时用于获取access_token以及openid的请求路径： https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code(最后一个参数不变)

        try {
            //Http类会在后面贴出链接
            json1 = Http.methodGet(httpurl, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonToken = JSON.parseObject(json1);

        String access_token = jsonToken.getString("access_token");
        String openid = jsonToken.getString("openid");

        //通过access_token和openid请求获取用户信息https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        try {
            json2 = Http.methodGet("https://api.weixin.qq.com/sns/userinfo", "access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN");
        } catch (Exception e) {
            e.printStackTrace();
        }

        WeixinUser weixinUser = JSON.parseObject(json2, WeixinUser.class);


        // 推广id，用于判断是否是某个用户的拉新用户
        Object promoter_id = request.getSession().getAttribute("promoter_id");
        if (null != promoter_id) {
            weixinUser.setPromoterId(Integer.valueOf(String.valueOf(promoter_id)));
        }

        // 存储微信用户
        weixinUserService.modifyWeixinUser(weixinUser);

        // 将设置的emoji格式转换为正常格式
        weixinUser.setNickname(EmojiParser.parseToUnicode(weixinUser.getNickname()));

        request.getSession().setAttribute("weixinUser", weixinUserService.queryWeixinUserById(weixinUser.getId()));
        request.setCharacterEncoding("UTF-8");

        return "redirect:/weixin/h5/" + state;
    }

}
