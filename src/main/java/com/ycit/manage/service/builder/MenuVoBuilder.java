package com.ycit.manage.service.builder;

import com.ycit.manage.bean.modal.Menu;
import com.ycit.manage.bean.vo.MenuVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 菜单实体 vo 构建
 * <p>
 * Created by xlch at 2018/5/15
 */
@Service
public class MenuVoBuilder {

    private List<Menu> loopMenus;

    public List<MenuVo> buildMenuTree(List<Menu> menus) {
        List<MenuVo> menuVos = buildTree(menus);
        List<MenuVo> clearMenu = clearTree(menuVos);
        Collections.sort(clearMenu);
        for (MenuVo menuVo:clearMenu) {
            if (!CollectionUtils.isEmpty(menuVo.getChildren())) {
                Collections.sort(menuVo.getChildren());
            }
        }
        return clearMenu;
    }

    public List<MenuVo> buildTree(List<Menu> menus) {
        List<MenuVo> menuVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menus)) {
            for (Menu menu:menus) {
                loopMenus = new ArrayList<>();
                MenuVo menuVo = MenuVo.fromBean(menu);
                List<Menu> menuList = this.findChildren(menus, menu.getId());
                if (menuList.size() > 0) {
                    menuVo.setChildren(menuList);
                }
                menuVos.add(menuVo);
            }
        }
        return menuVos;
    }

    /**
     * 查询子节点集合
     * @param menus
     * @param parentId
     * @return
     */
    public List<Menu> findChildren(List<Menu> menus, int parentId) {
        for (Iterator<Menu> iterator = menus.iterator();iterator.hasNext();) {
            Menu menu = iterator.next();
            if (menu.getpId() != 0 && parentId == menu.getpId()) {
                loopNodeChildren(menus, menu, parentId);
            }
        }
        return loopMenus;
    }

    /**
     * 遍历一个节点的子节点
     * @param menus
     * @param menu
     * @param pid
     */
    public void loopNodeChildren(List<Menu> menus, Menu menu, int pid) {
        List<Menu> childrenMenus = this.getChildren(menus, menu);
        if (childrenMenus.size() > 0) {
            if (menu.getpId() == pid) {
                loopMenus.add(menu);
            }
            Iterator<Menu> iterator = childrenMenus.iterator();
            while (iterator.hasNext()) {
                Menu menu1 = iterator.next();
                loopNodeChildren(menus, menu1, pid);
            }
        } else {
            if (menu.getpId() == pid) {
                loopMenus.add(menu);
            }
        }
    }

    /**
     * 得到子节点列表
     * @param menus
     * @param menu
     * @return
     */
    public List<Menu> getChildren(List<Menu> menus, Menu menu) {
        List<Menu> menus1 = new ArrayList<>();
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            Menu menu1 = iterator.next();
            if (menu1.getpId() == menu.getId()){
                menus1.add(menu1);
            }
        }
        return menus1;
    }

    public List<MenuVo> clearTree(List<MenuVo> menuVos) {
        List<MenuVo> firstLevelMenus = new ArrayList<>();
        for (MenuVo menuVo:menuVos) {
            int level = menuVo.getLevel();
            if (level == 1) {
                firstLevelMenus.add(menuVo);
            }
        }
        return firstLevelMenus;
    }

}
