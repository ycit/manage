package com.ycit.bean.modal;

import com.ycit.bean.base.BaseBean;

/**
 * 部门
 * <p>
 * Created by xlch at 2018/4/25
 */
public class Dept extends BaseBean {

    private Integer id;
    private String fullName;
    private String nickname;
    private int pid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
