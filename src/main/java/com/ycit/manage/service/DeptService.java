package com.ycit.manage.service;

import com.ycit.manage.bean.modal.Dept;
import com.ycit.manage.bean.vo.ZTreeNode;
import com.ycit.manage.mapper.DeptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

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

    public List<Dept> finds() {
        return deptMapper.finds();
    }

    public Dept insert(Dept dept) {
        deptMapper.insert(dept);
        return this.findById(dept.getId());
    }

    @Transactional
    public Dept doInsert(Dept dept) {
        Dept deptDb = this.insert(dept);
        int id = deptDb.getId();
        int pid = dept.getpId();
        Dept deptParent = this.findById(pid);
        String pidsParent = deptParent.getpIds();
        String pids = pidsParent + "," + "[" + id + "]";
        this.updatePidsById(id, pids);
        return this.findById(id);
    }

    public int updatePidsById(int id, String pids) {
        return deptMapper.updatePIdsById(id, pids);
    }

    public int deleteById(int id) {
        return deptMapper.deleteById(id);
    }

    public int update(Dept dept) {
        return deptMapper.update(dept);
    }

    public Dept doUpdate(Dept dept) {
        int id = dept.getId();
        int pid = dept.getpId();
        Dept deptParent = this.findById(pid);
        String pidsParent = deptParent.getpIds();
        String pids = pidsParent + "," + "[" + id + "]";
        dept.setpIds(pids);
        this.update(dept);
        return this.findById(id);
    }

}
