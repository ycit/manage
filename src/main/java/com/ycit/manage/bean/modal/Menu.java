package com.ycit.manage.bean.modal;

import com.ycit.manage.bean.base.BaseBean;
import com.ycit.manage.bean.vo.MenuVo;

/**
 * 菜单实体bean
 * <p>
 * Created by xlch at 2018/5/14
 */
public class Menu extends BaseBean implements Comparable {

    private int id;
    private String name;
    private String icon;
    private String url;
    private int level;
    private Integer num;
    private int pId;
    private String code;

    @Override
    public int compareTo(Object o) {
        Menu menuVo = (Menu) o;
        Integer num = menuVo.getNum();
        return this.num.compareTo(num);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", level=" + level +
                ", num=" + num +
                ", pId=" + pId +
                ", code='" + code + '\'' +
                '}';
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
