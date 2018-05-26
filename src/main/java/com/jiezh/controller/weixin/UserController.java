package com.jiezh.controller.weixin;

import com.jiezh.entity.User;
import com.jiezh.pub.web.WebAction;
import com.jiezh.service.weixin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    /**
     * 进入用户列表
     * @return
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

}
