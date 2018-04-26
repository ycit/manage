package com.ycit.manage.bean.criteria;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 字典表表单
 *
 * @author xlch
 * @Date 2018-03-23 11:03
 */
public class DictForm {

    @NotEmpty(message = "字典表名称不能为空")
    @Length(max = 50, message = "字典表名称长度不能超过50个字符")
    private String name;
    @Length(max = 80, message = "备注信息长度不能超过80个字符")
    private String description;

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
