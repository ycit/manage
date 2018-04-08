package com.ycit.bean.modal.dict;

import com.ycit.bean.base.Base;
import com.ycit.bean.criteria.DictForm;
import org.springframework.beans.BeanUtils;

/**
 * 字典表管理表
 *
 * @author xlch
 * @Date 2018-03-23 10:12
 */
public class Type extends Base {

    private int id;
    private String name;
    private String description;

    public static Type fromBean(DictForm dictForm) {
        Type type = new Type();
        BeanUtils.copyProperties(dictForm, type);
        return type;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
