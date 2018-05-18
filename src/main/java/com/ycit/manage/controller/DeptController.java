package com.ycit.manage.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.ycit.manage.bean.base.ApiResponse;
import com.ycit.manage.bean.modal.Dept;
import com.ycit.manage.bean.vo.ZTreeNode;
import com.ycit.manage.service.DeptService;
import com.ycit.manage.service.builder.DeptVoBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 部门控制层
 * <p>
 * Created by xlch at 2018/4/26
 */
@Controller
@RequestMapping("/back/dept")
public class DeptController extends BaseController {

    public DeptService deptService;

    public DeptVoBuilder deptVoBuilder;

    @Resource
    public void setDeptVoBuilder(DeptVoBuilder deptVoBuilder) {
        this.deptVoBuilder = deptVoBuilder;
    }

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

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "/dept/dept";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Dept> finds() {
        return deptService.finds();
    }

    @RequestMapping(value = "/add/home", method = RequestMethod.GET)
    public String addHome() {
        return "/dept/dept-add";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Dept insert(Dept dept) {
        return deptService.doInsert(dept);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ApiResponse<Integer> delete(@RequestParam("id")int id) {
        if (id == 1) {
            return error(400, "无法删除总公司");
        }
        List<Integer> results = new ArrayList<>();
        results.add(deptService.deleteById(id));
        return success(results, 1);
    }

    @RequestMapping(value = "/edit/home", method = RequestMethod.GET)
    public String editHome(Model model, @RequestParam("id")int id) {
        model.addAttribute("dept", deptVoBuilder.buildOne(deptService.findById(id)));
        return "/dept/dept-edit";
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Dept edit(Dept dept) {
        return deptService.doUpdate(dept);
    }

}
