package com.ycit.manage.service.builder;

import com.ycit.manage.bean.modal.Dept;
import com.ycit.manage.bean.vo.DeptVo;
import com.ycit.manage.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * deptVo 构建类
 * <p>
 * Created by xlch at 2018/5/2
 */
@Service
public class DeptVoBuilder {

    private DeptService deptService;

    @Resource
    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public DeptVo buildOne(Dept dept) {
        DeptVo deptVo = DeptVo.fromBean(dept);
        Dept parentDept = deptService.findById(dept.getpId());
        deptVo.setParentDept(parentDept);
        return deptVo;
    }

}
