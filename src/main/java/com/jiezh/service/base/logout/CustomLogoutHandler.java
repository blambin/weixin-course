package com.jiezh.service.base.logout;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 退出成功后在库中标识
 * 
 * @author liangds
 *
 */
public class CustomLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse responses, Authentication paramAuthentication) {
        System.out.println("delSessionId>>>:" + request.getAttribute("delSessionId"));
    }

}
