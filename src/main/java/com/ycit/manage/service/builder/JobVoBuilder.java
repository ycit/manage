package com.ycit.manage.service.builder;

import com.ycit.manage.bean.modal.Dept;
import com.ycit.manage.bean.modal.Job;
import com.ycit.manage.bean.vo.JobVo;
import com.ycit.manage.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * jobVo 构造器
 * <p>
 * Created by xlch at 2018/5/2
 */
@Service
public class JobVoBuilder {

    private DeptService deptService;

    @Resource
    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public JobVo buildOne(Job job) {
        JobVo jobVo = JobVo.fromBean(job);
        Dept dept = deptService.findById(job.getDeptId());
        jobVo.setDept(dept);
        return jobVo;
    }

    public List<JobVo> buildList(List<Job> jobs) {
        List<JobVo> jobVos = new ArrayList<>();
        jobs.forEach(job -> {
            jobVos.add(buildOne(job));
        });
        return jobVos;
    }

}
