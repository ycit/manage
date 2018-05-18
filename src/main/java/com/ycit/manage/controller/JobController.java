package com.ycit.manage.controller;

import com.ycit.manage.bean.base.ApiResponse;
import com.ycit.manage.bean.criteria.JobForm;
import com.ycit.manage.bean.modal.Job;
import com.ycit.manage.bean.vo.JobVo;
import com.ycit.manage.service.JobService;
import com.ycit.manage.service.builder.JobVoBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 职位 控制层
 * <p>
 * Created by xlch at 2018/4/27
 */
@Controller
@RequestMapping("/back/jobs")
public class JobController extends BaseController{

    private JobService jobService;

    private JobVoBuilder jobVoBuilder;

    @Resource
    public void setJobVoBuilder(JobVoBuilder jobVoBuilder) {
        this.jobVoBuilder = jobVoBuilder;
    }

    @Resource
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @ResponseBody
    @RequestMapping(value = "/{deptId}", method = RequestMethod.GET)
    public List<Job> findByDeptId(@PathVariable("deptId")int deptId) {
        return jobService.findByDeptId(deptId);
    }

    @RequestMapping("/home")
    public String home() {
        return "/job/job";
    }

    /**
     * 查询所有岗位
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public List<JobVo> finds(JobForm jobForm) {
        List<Job> jobs = jobService.finds(jobForm);
        return jobVoBuilder.buildList(jobs);
    }

    /**
     * 新增岗位页面跳转
     * @return
     */
    @RequestMapping("/add/home")
    public String addHome() {
        return "/job/job-add";
    }

    /**
     * 新增岗位请求
     * @param job
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Job add(Job job) {
        return jobService.insert(job);
    }

    /**
     * 编辑岗位页面跳转
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/edit/home")
    public String editHome(Model model, @RequestParam("id")int id) {
        model.addAttribute("job", jobVoBuilder.buildOne(jobService.findById(id)));
        return "/job/job-edit";
    }

    /**
     * 编辑岗位操作
     * @param job
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public int update(Job job) {
        return jobService.update(job);
    }

    /**
     * 删除岗位请求
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ApiResponse delete(@RequestParam("id")int id) {
        if (id == 1) {
            return error(400, "无法删除超级管理员岗位");
        }
        int num = jobService.delete(id);
        List<Integer> result = new ArrayList<>();
        result.add(num);
        return success(result, 1);
    }

}
