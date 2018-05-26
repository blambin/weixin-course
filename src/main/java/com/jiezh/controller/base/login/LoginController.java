package com.jiezh.controller.base.login;

import com.jiezh.entity.User;
import com.jiezh.pub.Log;
import com.jiezh.pub.web.WebAction;
import com.jiezh.service.weixin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录
 *
 * @author liangds
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/")
public class LoginController extends WebAction {

    Log log = new Log(LoginController.class);

    @Autowired
    UserService userService;

    /**
     * 进微信首页
     *
     * @return
     */
    @RequestMapping("index")
    public String index() throws Exception{
        /*String url = "http://ketang.hbgstzgl.com/index.do";
        Map<String, Object> map = new HashMap<>();
        getWeixinMap(request, map, url, model);*/
        // return new ModelAndView("weixin/h5/auth");

        return "redirect:/weixin/h5/main.do";
    }

    /*@RequestMapping("index")
    public ModelAndView index() throws Exception{
        *//*String url = "http://ketang.hbgstzgl.com/index.do";
        Map<String, Object> map = new HashMap<>();
        getWeixinMap(request, map, url, model);*//*
        return new ModelAndView("weixin/h5/auth");
    }*/

    /**
     * 进登录页
     *
     * @return
     */
    @RequestMapping("main")
    public ModelAndView main() throws Exception{
        return new ModelAndView("base/login/index");
    }

    /**
     * 登录成功页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("login")
    public ModelAndView login() throws Exception {
        ModelAndView mv = new ModelAndView("base/login/main");

        User user = (User) this.getBean(User.class);

        User loginUser = userService.queryUserByLoginAccount(user.getLoginAccount());
        if (loginUser == null) {
            return main();
        }

        if (!loginUser.getPassword().equals(user.getPassword())) {
            return main();
        }

        session.setAttribute("user", user);

        return mv;
    }

}
