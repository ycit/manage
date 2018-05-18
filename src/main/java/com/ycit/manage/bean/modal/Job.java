package com.ycit.manage.bean.modal;

import com.ycit.manage.bean.base.BaseBean;

/**
 * 职位
 * <p>
 * Created by xlch at 2018/4/25
 */
public class Job extends BaseBean {

    private Integer id;
    private String fullName;
    private String nickname;
    private int deptId;

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", deptId=" + deptId +
                '}';
    }

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

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
}
