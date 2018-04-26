package com.ycit.manage.service;

import com.ycit.manage.bean.modal.Dept;
import com.ycit.manage.bean.vo.ZTreeNode;
import com.ycit.manage.mapper.DeptMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门 service 层
 * <p>
 * Created by xlch at 2018/4/26
 */
@Service
public class DeptService {

    private DeptMapper deptMapper;

    @Resource
    public void setDeptMapper(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    public Dept findById(int id) {
        return deptMapper.findById(id);
    }

    public List<ZTreeNode> findTree() {
        return deptMapper.findTree();
    }

}
