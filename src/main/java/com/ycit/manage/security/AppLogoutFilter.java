package com.ycit.manage.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 登出过滤器
 *
 * @author xlch
 * @Date 2018-03-21 17:10
 */
public class AppLogoutFilter extends LogoutFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutFilter.class);

    private static final String REDIRECT_URL = "/back/login";


    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (SessionException ise) {
            LOGGER.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        issueRedirect(request, response, REDIRECT_URL);
        return false;
    }

}
