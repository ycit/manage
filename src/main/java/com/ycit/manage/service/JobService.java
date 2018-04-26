package com.ycit.manage.service;

import com.ycit.manage.bean.modal.Job;
import com.ycit.manage.mapper.JobMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

}
