package com.ycit.manage.service.builder;

import com.ycit.manage.bean.modal.JobMenu;
import com.ycit.manage.bean.vo.JobMenuVo;
import com.ycit.manage.service.JobMenuService;
import com.ycit.manage.service.JobService;
import com.ycit.manage.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建jobmenu
 * <p>
 * Created by xlch at 2018/5/15
 */
@Service
public class JobMenuVoBuilder {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobMenuService jobMenuService;

    public JobMenuVo buildByJobId(int jobId) {
        JobMenuVo jobMenuVo = new JobMenuVo();
        jobMenuVo.setJob(jobService.findById(jobId));
        jobMenuVo.setMenus(jobMenuService.findMenuByJobId(jobId));
        return jobMenuVo;
    }

}
