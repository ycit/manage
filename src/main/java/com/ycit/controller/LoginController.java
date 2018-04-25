package com.ycit.controller;

import com.ycit.bean.criteria.LoginForm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * 登入登出 控制层
 *
 * @author xlch
 * @Date 2018-03-21 12:51
 */
@Controller
@RequestMapping("/back")
public class LoginController {

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginHome() {
        return "/login";
    }

    /**
     * 发起登录请求，form表单提交 为 post
     *
     * @param loginForm 表单数据
     * @param result    数据是否合法验证
     * @return 返回登录结果
     */
    @ResponseBody
    @PostMapping(value = "/login", produces = "application/json;charset=utf-8")
    public String login(@Valid LoginForm loginForm, BindingResult result) {
        if (result.hasErrors()) {
            return result.getAllErrors().stream().map(
                    ObjectError::getDefaultMessage
            ).collect(Collectors.joining(","));
        }
        UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getUsername(), loginForm.getPassword(), loginForm.isRemember());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return "没有找到用户信息，请用户名是否正确。";
        } catch (IncorrectCredentialsException ice) {
            return "密码与帐号不匹配，请确认是否输入了正确的密码。注意键盘是否在大写输入状态。";
        } catch (AuthenticationException ae) {
            return "登录信息不正确，请联系管理员。";
        } catch (Exception e) {
            return "服务内部错误，请联系管理员。";
        }
        return "success";
    }

    /**
     * 登出请求，shiro 移除 session 跳转到登录页面
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            currentUser.logout();
        }
        return "redirect:/back/login";
    }

}
