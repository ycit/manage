package com.ycit.manage.bean.vo;

/**
 * 键值对 bean
 * <p>
 * Created by xlch at 2018/5/4
 */
public class KeyValue {

    private String name;
    private String value;

    public KeyValue() {
    }

    public KeyValue(String name, String value) {
        this.name = name;
        this.value = value;
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
}
