package com.jiezh.filter;

import com.baidu.ueditor.ActionEnter;
import com.jiezh.pub.Env;
import com.jiezh.pub.util.PropertiesUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author yclimb
 * @date 2018/5/23
 *
 * UEditor controller.jsp 拦截器
 * 初始化 config,json
 *
 */
/*@WebFilter(filterName = "UEditorFilter", urlPatterns = "/ueditor/jsp/controller.jsp")*/
public class UEditorFilter implements Filter {
    private FilterConfig config;

    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html");
        // String rootPath = config.getServletContext().getRealPath("/");
        String rootPath = PropertiesUtil.getUPLOAD("store_root_path") + Env.getWebRoot() + File.separator;

        System.out.println(rootPath);

        String res = new ActionEnter(request, rootPath).exec();
        response.getWriter().write(res);

    }
    public void init(FilterConfig config) {
        this.config = config;
    }

}