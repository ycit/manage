package com.ycit.manage.bean.vo;

import com.ycit.manage.bean.modal.Job;
import com.ycit.manage.bean.modal.JobMenu;
import com.ycit.manage.bean.modal.Menu;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 岗位对应菜单
 * <p>
 * Created by xlch at 2018/5/14
 */
public class JobMenuVo {

    private List<Menu> menus;
    private Job job;

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
