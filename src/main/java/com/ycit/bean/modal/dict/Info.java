package com.ycit.bean.modal.dict;

/**
 * 专卖店表
 *
 * @author xlch
 * @Date 2018-03-23 9:56
 */
public class Info extends DictBase {

    public Info() {
    }

    public Info(int typeId, String name) {
        super(typeId, name);
    }

    public Info(int id, int typeId, String code, String name) {
        super(id, typeId, code, name);
    }
}
