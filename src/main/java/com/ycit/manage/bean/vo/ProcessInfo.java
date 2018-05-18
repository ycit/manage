package com.ycit.manage.bean.vo;

/**
 * 进程实体
 * <p>
 * Created by xlch at 2018/5/18
 */
public class ProcessInfo {

    private String pid;

    private String user;

    private String startTime;

    private String memSize;

    private String memUse;

    private String memhare;

    private String state;

    private String cpuTime;

    private String name;

    @Override
    public String toString() {
        return "ProcessInfo{" +
                "pid='" + pid + '\'' +
                ", user='" + user + '\'' +
                ", startTime='" + startTime + '\'' +
                ", memSize='" + memSize + '\'' +
                ", memUse='" + memUse + '\'' +
                ", memhare='" + memhare + '\'' +
                ", state='" + state + '\'' +
                ", cpuTime='" + cpuTime + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getMemSize() {
        return memSize;
    }

    public void setMemSize(String memSize) {
        this.memSize = memSize;
    }

    public String getMemUse() {
        return memUse;
    }

    public void setMemUse(String memUse) {
        this.memUse = memUse;
    }

    public String getMemhare() {
        return memhare;
    }

    public void setMemhare(String memhare) {
        this.memhare = memhare;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(String cpuTime) {
        this.cpuTime = cpuTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
