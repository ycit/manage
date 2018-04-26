package com.ycit.manage.service.builder;

import com.ycit.manage.bean.modal.Dept;
import com.ycit.manage.bean.modal.Job;
import com.ycit.manage.bean.modal.User;
import com.ycit.manage.bean.vo.UserVo;
import com.ycit.manage.service.DeptService;
import com.ycit.manage.service.JobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * uservo 构建器
 * <p>
 * Created by xlch at 2018/4/26
 */
@Service
public class UserVoBuilder {

    private DeptService deptService;

    private JobService jobService;

    @Resource
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @Resource
    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public UserVo buildOne(User user) {
        UserVo userVo = UserVo.fromBean(user);
        Dept dept = deptService.findById(user.getDeptId());
        Job job = jobService.findById(user.getJobId());
        userVo.setDept(dept);
        userVo.setJob(job);
        return userVo;
    }

    public List<UserVo> buildList(List<User> users) {
        List<UserVo> userVos = new ArrayList<>();
        users.forEach(user -> {
            UserVo userVo = buildOne(user);
            userVos.add(userVo);
        });
        return userVos;
    }

}
