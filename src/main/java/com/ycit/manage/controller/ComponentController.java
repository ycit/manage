package com.ycit.manage.controller;

import com.ycit.manage.bean.modal.Task;
import com.ycit.manage.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 组件管理控制层
 * <p>
 * Created by xlch at 2018/5/3
 */
@Controller
@RequestMapping("/back/component")
public class ComponentController {

    private TaskService taskService;

    @Resource
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 组件页面跳转
     * @return
     */
    @RequestMapping("/home")
    public String home() {
        return "/component/component";
    }

    /**
     * 定时任务查询
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public List<Task> findList() {
        return taskService.findAll();
    }

    /**
     * 新增定时任务页面
     * @return
     */
    @RequestMapping("/add/home")
    public String addHome() {
        return "/component/component-add";
    }

    /**
     * 新增定时任务请求
     * @param task
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Task add(Task task) {
        return taskService.insert(task);
    }

    /**
     * 编辑定时任务 页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit/home")
    public String editHome(@RequestParam("id")int id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        return "/component/component-edit";
    }

    /**
     * 编辑定时任务 请求
     * @param task
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public int edit(Task task) {
        return taskService.updateById(task);
    }

    /**
     * 删除定时任务
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete/{id}")
    public int delete(@PathVariable("id")int id) {
        return taskService.deleteById(id);
    }

}
