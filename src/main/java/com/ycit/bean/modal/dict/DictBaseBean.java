package com.ycit.bean.modal.dict;

import com.ycit.bean.base.BaseBean;

/**
 *  字典基类
 * @author xlch
 * @Date 2018-03-22 11:02
 */
public class DictBaseBean extends BaseBean {

    private int id;
    private int typeId;
    private String code;
    private String name;

    public DictBaseBean() {
    }

    public DictBaseBean(int typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public DictBaseBean(int id, int typeId, String code, String name) {
        this.id = id;
        this.typeId = typeId;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
