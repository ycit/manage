package com.ycit.manage.bean.vo;

import com.ycit.manage.bean.modal.Dept;
import org.springframework.beans.BeanUtils;

/**
 * 部门页面bean
 * <p>
 * Created by xlch at 2018/5/2
 */
public class DeptVo extends Dept {

    private Dept parentDept;

    public static DeptVo fromBean(Dept dept) {
        DeptVo deptVo = new DeptVo();
        BeanUtils.copyProperties(dept, deptVo);
        return deptVo;
    }

    public Dept getParentDept() {
        return parentDept;
    }

    public void setParentDept(Dept parentDept) {
        this.parentDept = parentDept;
    }
}
