package com.ycit.manage.service;

import com.ycit.manage.bean.modal.Menu;
import com.ycit.manage.bean.vo.ZTreeNode;
import com.ycit.manage.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 菜单相关  servie
 * <p>
 * Created by xlch at 2018/5/14
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;


    public List<ZTreeNode> findTreeByMenuId(List<Integer> menuIds) {
        return menuMapper.findTreeByMenuId(menuIds);
    }

    public List<ZTreeNode> findOriginalTree() {
        return menuMapper.findOriginalTree();
    }

}
