package com.ycit.manage.service;

import com.ycit.manage.bean.criteria.JobForm;
import com.ycit.manage.bean.modal.Job;
import com.ycit.manage.mapper.JobMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 职位 service 层
 * <p>
 * Created by xlch at 2018/4/26
 */
@Service
public class JobService {

    private JobMapper jobMapper;

    @Resource
    public void setJobMapper(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    public Job findById(int id) {
        return jobMapper.findById(id);
    }

    public List<Job> findByDeptId(int deptId) {
        return jobMapper.findByDeptId(deptId);
    }

    public List<Job> finds(JobForm jobForm) {
        return jobMapper.finds(jobForm);
    }

    public Job insert(Job job) {
        jobMapper.insert(job);
        return this.findById(job.getId());
    }

    public int delete(int id) {
        return jobMapper.deleteById(id);
    }

    public int update(Job job) {
        return jobMapper.update(job);
    }

}
