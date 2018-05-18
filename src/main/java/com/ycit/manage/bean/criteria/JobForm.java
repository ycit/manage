package com.ycit.manage.bean.criteria;

/**
 * 岗位查询表单
 * <p>
 * Created by xlch at 2018/5/3
 */
public class JobForm {

    private String name;
    private int deptId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
}
