package com.ycit.manage.bean.modal;

/**
 * 系统配置
 * <p>
 * Created by xlch at 2018/5/7
 */
public class Config {

    private int id;
    private String name;
    private String value;
    private String showName;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}
