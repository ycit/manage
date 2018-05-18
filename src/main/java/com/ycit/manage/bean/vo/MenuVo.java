package com.ycit.manage.bean.vo;

import com.ycit.manage.bean.modal.Menu;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 菜单vo 页面bean
 * <p>
 * Created by xlch at 2018/5/14
 */
public class MenuVo  implements Comparable {

    private int id;
    private String name;
    private String icon;
    private String url;
    private int level;
    private Integer num;
    private int pId;
    private String code;
    private List<Menu> children;

    public static MenuVo fromBean(Menu menu) {
        MenuVo menuVo = new MenuVo();
        BeanUtils.copyProperties(menu, menuVo);
        return menuVo;
    }

    @Override
    public int compareTo(Object o) {
        MenuVo menuVo = (MenuVo) o;
        Integer num = menuVo.getNum();
        return this.num.compareTo(num);
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
