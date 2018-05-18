package com.ycit.manage.service;

import com.google.common.base.Splitter;
import com.ycit.manage.bean.modal.JobMenu;
import com.ycit.manage.bean.modal.Menu;
import com.ycit.manage.bean.vo.ZTreeNode;
import com.ycit.manage.mapper.JobMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 岗位菜单 服务层
 * <p>
 * Created by xlch at 2018/5/14
 */
@Service
public class JobMenuService {

    private JobMenuMapper jobMenuMapper;

    @Resource
    public void setJobMenuMapper(JobMenuMapper jobMenuMapper) {
        this.jobMenuMapper = jobMenuMapper;
    }

    public List<JobMenu> findByJobId(int jobId) {
        return jobMenuMapper.findByJobId(jobId);
    }

    public List<Menu> findMenuByJobId(int jobId) {
        return jobMenuMapper.findMenuByJobId(jobId);
    }

    public List<Integer> findMenuIdByJobId(int jobId) {
        return jobMenuMapper.findMenuIdByJobId(jobId);
    }

    public int insertBatch(List<JobMenu> jobMenus) {
        return jobMenuMapper.insertBatch(jobMenus);
    }

    public int deleteByJobId(int jobId) {
        return jobMenuMapper.deleteByJobId(jobId);
    }

    @Transactional
    public int doInsert(int jobId, String menuIds) {
        this.deleteByJobId(jobId);
        List<String> menuIdList = Splitter.on(",").splitToList(menuIds);
        List<JobMenu> jobMenus = new ArrayList<>();
        menuIdList.forEach(menuId -> {
            JobMenu jobMenu = new JobMenu(jobId, Integer.valueOf(menuId));
            jobMenus.add(jobMenu);
        });
        return this.insertBatch(jobMenus);
    }



}
