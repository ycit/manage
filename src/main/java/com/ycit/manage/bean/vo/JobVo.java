package com.ycit.manage.bean.vo;

import com.ycit.manage.bean.modal.Dept;
import com.ycit.manage.bean.modal.Job;
import org.springframework.beans.BeanUtils;

/**
 * job 页面bean
 * <p>
 * Created by xlch at 2018/5/2
 */
public class JobVo extends Job {

    private Dept dept;

    public static JobVo fromBean(Job job) {
        JobVo jobVo = new JobVo();
        BeanUtils.copyProperties(job, jobVo);
        return jobVo;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}
