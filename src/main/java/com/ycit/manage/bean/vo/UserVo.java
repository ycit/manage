package com.ycit.manage.bean.vo;

import com.ycit.manage.bean.modal.Dept;
import com.ycit.manage.bean.modal.Job;
import com.ycit.manage.bean.modal.User;
import org.springframework.beans.BeanUtils;

/**
 * user 包装对象
 * <p>
 * Created by xlch at 2018/4/26
 */
public class UserVo extends User {

    private Dept dept;
    private Job job;

    public static UserVo fromBean(User user) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
