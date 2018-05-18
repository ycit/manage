package com.ycit.manage.bean.modal;

import java.util.List;

/**
 * 岗位菜单表
 * <p>
 * Created by xlch at 2018/5/14
 */
public class JobMenu {

    private int id;
    private int jobId;
    private int menuId;

    public JobMenu() {
    }

    public JobMenu(int jobId, int menuId) {
        this.jobId = jobId;
        this.menuId = menuId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
}
