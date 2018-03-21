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

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/users")
    public String home(Model model) {
        List<User> users = userService.finds();
        model.addAttribute("users", users);
        return "/user-manager";
    }

    @ResponseBody
    @RequestMapping(value = "/users/inpour", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public int inpour(@RequestParam("num")int num, @RequestParam("id")int id) {
        int size = userService.updateBalance(id, num);
        User user = userService.findById(id);
        return user.getBalance();
    }

    @ResponseBody
    @RequestMapping(value = "/users/delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public int delete(@RequestParam("id")int id) {
        return userService.deleteById(id);
    }

}
