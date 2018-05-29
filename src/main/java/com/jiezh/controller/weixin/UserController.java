package com.jiezh.controller.weixin;

import com.github.pagehelper.PageInfo;
import com.jiezh.entity.User;
import com.jiezh.entity.WeixinUser;
import com.jiezh.pub.Env;
import com.jiezh.pub.web.WebAction;
import com.jiezh.service.weixin.UserService;
import com.jiezh.service.weixin.WeixinUserService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户控制类
 *
 * @author yclimb
 * @date 2018/4/30
 */
@Controller
@Scope("prototype")
@RequestMapping("/weixin/user")
public class UserController extends WebAction {

    @Autowired
    private UserService userService;

    @Autowired
    private WeixinUserService weixinUserService;

    /**
     * 进入用户列表
     * @return mv
     */
    @RequestMapping("/queryUserList")
    public ModelAndView queryUserList() {
        ModelAndView mv = new ModelAndView("weixin/user/user_list");

        User user = (User) getBean(User.class);

        int currenPage = 1;
        if (request.getParameter("currenPage") != null && !"".equals(request.getParameter("currenPage"))) {
            currenPage = Integer.parseInt(request.getParameter("currenPage"));
        }

        mv.addObject("page", userService.queryUserPage(currenPage, user));
        mv.addObject("findObj", user);

        return mv;
    }

    /**
     * 进入用户分销列表
     * @return mv
     */
    @RequestMapping("/queryUserPromoterList")
    public ModelAndView queryUserPromoterList() {
        ModelAndView mv = new ModelAndView("weixin/user/user_promoter_list");

        WeixinUser weixinUser = (WeixinUser) getBean(WeixinUser.class);

        int currenPage = 1;
        if (request.getParameter("currenPage") != null && !"".equals(request.getParameter("currenPage"))) {
            currenPage = Integer.parseInt(request.getParameter("currenPage"));
        }

        PageInfo<WeixinUser> pageList = weixinUserService.queryWeixinUserPage(currenPage, Env.PAGE_SIZE, weixinUser);
        List<WeixinUser> weixinUserList = new ArrayList<>();
        for (WeixinUser user : pageList.getList()) {
            // 将设置的emoji格式转换为正常格式
            user.setNickname(EmojiParser.parseToUnicode(user.getNickname()));
            weixinUserList.add(user);
        }

        pageList.setList(weixinUserList);
        mv.addObject("page", pageList);
        mv.addObject("findObj", weixinUser);

        return mv;
    }

}
