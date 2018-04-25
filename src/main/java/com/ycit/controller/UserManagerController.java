package com.ycit.controller;

import com.ycit.bean.modal.User;
import com.ycit.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理层
 *
 * @author xlch
 * @Date 2018-03-21 13:32
 */
@Controller
@RequestMapping("/back")
public class UserManagerController {

    private UserService userService;

    /**
     * setter 方法 注入 spring 管理的 service 层
     */
    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户管理页面，返回用户jsp页面，spring mvc viewer 处理
     * @param model
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String home(Model model) {
        return "/user-manager";
    }

    /**
     * 查询用户数据请求
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public List<User> users() {
       return userService.finds();
    }


    /**
     * 删除用户请求
     * @param id 用户 id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/users/delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public int delete(@RequestParam("id")int id) {
        return userService.deleteById(id);
    }

}
