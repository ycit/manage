package com.ycit.manage.controller;

import com.ycit.manage.bean.modal.Menu;
import com.ycit.manage.bean.modal.User;
import com.ycit.manage.bean.vo.MenuVo;
import com.ycit.manage.bean.vo.ZTreeNode;
import com.ycit.manage.service.JobMenuService;
import com.ycit.manage.service.MenuService;
import com.ycit.manage.service.UserService;
import com.ycit.manage.service.builder.MenuVoBuilder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 菜单控制器
 * <p>
 * Created by xlch at 2018/5/15
 */
@Controller
@RequestMapping("/back")
public class MenuController {

    @Autowired
    private UserService userService;

    @Autowired
    private JobMenuService jobMenuService;

    @Autowired
    private MenuVoBuilder menuVoBuilder;

    @Autowired
    private MenuService menuService;


    @ResponseBody
    @RequestMapping("/menus")
    public List<MenuVo> menuVos() {
        User user = userService.findCurrent();
        List<Menu> menuList = jobMenuService.findMenuByJobId(user.getJobId());
        return menuVoBuilder.buildMenuTree(menuList);
    }

    /**
     * 获取某个岗位的菜单列表
     * @param jobId
     * @return
     */
    @ResponseBody
    @RequestMapping("/menus/{jobId}")
    public List<ZTreeNode> list(@PathVariable("jobId")int jobId) {
        List<Integer> menuIds = jobMenuService.findMenuIdByJobId(jobId);
        if (!CollectionUtils.isEmpty(menuIds)) {
            return menuService.findTreeByMenuId(menuIds);
        } else {
            return menuService.findOriginalTree();
        }
    }

    @ResponseBody
    @RequestMapping("/menus/{jobId}/update")
    public int insertBatch(@RequestParam("menuIds")String menuIds, @PathVariable("jobId")int jobId) {
        if (StringUtils.isBlank(menuIds)) {
            jobMenuService.deleteByJobId(jobId);
            return 0;
        }
        return jobMenuService.doInsert(jobId, menuIds);
    }

}
