package com.ycit.manage.controller;

import com.ycit.manage.bean.modal.Job;
import com.ycit.manage.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 职位 控制层
 * <p>
 * Created by xlch at 2018/4/27
 */
@Controller
@RequestMapping("/back/jobs")
public class JobController {

    private JobService jobService;

    @Resource
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @ResponseBody
    @RequestMapping(value = "/{deptId}", method = RequestMethod.GET)
    public List<Job> findByDeptId(@PathVariable("deptId")int deptId) {
        return jobService.findByDeptId(deptId);
    }

}
