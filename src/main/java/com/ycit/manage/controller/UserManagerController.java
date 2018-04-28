package com.ycit.manage.controller;

import com.ycit.manage.bean.base.ApiResponse;
import com.ycit.manage.bean.criteria.PwForm;
import com.ycit.manage.bean.modal.User;
import com.ycit.manage.bean.vo.UserVo;
import com.ycit.manage.service.UserService;
import com.ycit.manage.service.builder.UserVoBuilder;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理层
 *
 * @author xlch
 * @Date 2018-03-21 13:32
 */
@Controller
@RequestMapping("/back")
public class UserManagerController extends BaseController {

    private UserService userService;

    private UserVoBuilder userVoBuilder;

    @Resource
    public void setUserVoBuilder(UserVoBuilder userVoBuilder) {
        this.userVoBuilder = userVoBuilder;
    }

    /**
     * setter 方法 注入 spring 管理的 service 层
     */
    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 返回用户管理页面，spring mvc viewer 处理
     * @param model
     * @return
     */
    @RequestMapping(value = "/users/home", method = RequestMethod.GET)
    public String home(Model model) {
        return "/user/user-manage";
    }

    /**
     * 查询用户数据请求
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserVo> users() {
       List<User> users =  userService.finds();
       return userVoBuilder.buildList(users);
    }

    @RequestMapping(value = "/users/add/home", method = RequestMethod.GET)
    public String addHome(Model model) {
        return "/user/user-add";
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

    /**
     * 新增用户请求
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public User insert(User user) {
        return userService.insert(user);
    }

    /**
     * 判断该用户名是否有效
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/users/valid", method = RequestMethod.GET)
    public boolean findByUsername(@RequestParam("username")String username, @RequestParam(value = "id", required = false, defaultValue = "-1")int id) {
        User user;
        if (id == -1) {
            user =  userService.findByUsername(username);
        } else {
            user = userService.findByIdAndUsername(id, username);
        }
        if (user == null) {
            return true;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping(value = "/users/img/upload")
    public User uploadImg(@RequestParam("img") MultipartFile img, @RequestParam("id")int id) {
        return userService.doUploadImg(id, img);
    }

    @RequestMapping(value = "/users/edit/home")
    public String editHome(@RequestParam("id")int id, Model model) {
        model.addAttribute("id", id);
        return "/user/user-edit";
    }

    @ResponseBody
    @RequestMapping(value = "/users/{id}")
    public User findById(@PathVariable("id")int id) {
        return userVoBuilder.buildOne(userService.findById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    public User edit(User user) {
        return userService.update(user);
    }

    @ResponseBody
    @RequestMapping(value = "/users/pw", method = RequestMethod.POST)
    public ApiResponse<String> updatePw(PwForm form) {
        String msg =  userService.doChangePw(form);
        if (StringUtils.isNotBlank(msg)) {
            return error(400, msg);
        } else {
            return success(new ArrayList(), 0);
        }
    }

}
