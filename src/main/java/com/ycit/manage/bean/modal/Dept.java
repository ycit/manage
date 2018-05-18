package com.ycit.manage.bean.modal;

import com.ycit.manage.bean.base.BaseBean;

/**
 * 部门
 * <p>
 * Created by xlch at 2018/4/25
 */
public class Dept extends BaseBean {

    private Integer id;
    private String fullName;
    private String nickname;
    private int level;
    private int pId;
    private String pIds;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpIds() {
        return pIds;
    }

    public void setpIds(String pIds) {
        this.pIds = pIds;
    }
}
