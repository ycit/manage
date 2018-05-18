package com.ycit.manage.bean.vo;

/**
 * 报表动态值
 * <p>
 * Created by xlch at 2018/5/4
 */
public class ReportVo {


    private String name;
    private String value;
    private String temp;

    public ReportVo() {
    }

    public ReportVo(String name, String value, String temp) {
        this.name = name;
        this.value = value;
        this.temp = temp;
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

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
