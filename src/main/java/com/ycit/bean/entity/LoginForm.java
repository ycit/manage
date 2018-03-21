package com.ycit.bean.entity;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 登录表单
 *
 * @author xlch
 * @Date 2018-03-21 12:53
 */
public class LoginForm {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private boolean remember;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

}
