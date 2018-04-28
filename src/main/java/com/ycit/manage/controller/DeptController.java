package com.ycit.manage.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.ycit.manage.bean.vo.ZTreeNode;
import com.ycit.manage.service.DeptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门控制层
 * <p>
 * Created by xlch at 2018/4/26
 */
@Controller
@RequestMapping("/back/dept")
public class DeptController {

    public DeptService deptService;

    @Resource
    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    @ResponseBody
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public List<ZTreeNode> findTree() {
        List<ZTreeNode> treeNodes =  deptService.findTree();
//        treeNodes.add(ZTreeNode.createParent());
        return treeNodes;
    }

}
