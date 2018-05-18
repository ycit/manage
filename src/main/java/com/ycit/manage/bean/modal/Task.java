package com.ycit.manage.bean.modal;

import com.ycit.manage.bean.base.BaseBean;

/**
 * 任务调度bean
 * <p>
 * Created by xlch at 2018/5/7
 */
public class Task extends BaseBean {

    private int id;
    private String name;
    private int groupId;
    private String groupName;
    private String cron;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
