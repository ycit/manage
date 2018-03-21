package com.ycit.security;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 应用 用户 未认证处理
 *
 * @author xlch
 * @Date 2018-03-21 12:36
 */
public class AppUserFilter extends UserFilter {

    private static final String ERROR_JSON = "{\"code\":401,\"url\":\"%s\"}";

    @Override
    protected boolean onAccessDenied(ServletRequest req, ServletResponse response) throws Exception {
        HttpServletRequest request = (HttpServletRequest) req;

        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpServletResponse.SC_OK);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().printf(ERROR_JSON, request.getRequestURI());

        return false;
    }

}
