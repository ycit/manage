package com.ycit.bean.vo;

import com.ycit.bean.modal.Goods;
import com.ycit.bean.modal.Store;
import com.ycit.bean.modal.dict.Info;
import org.springframework.beans.BeanUtils;

/**
 * 商品值对象(前端显示)
 *
 * @author xlch
 * @Date 2018-03-24 21:53
 */
public class GoodsVo extends Goods {

    private Store store;
    private Info brand;
    private Info category; // 电池种类
    private Info purpose; // 电池用途


    public static GoodsVo fromBean(Goods goods) {
        GoodsVo goodsVo = new GoodsVo();
        BeanUtils.copyProperties(goods, goodsVo);
        return goodsVo;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Info getBrand() {
        return brand;
    }

    public void setBrand(Info brand) {
        this.brand = brand;
    }


    public Info getCategory() {
        return category;
    }

    public void setCategory(Info category) {
        this.category = category;
    }

    public Info getPurpose() {
        return purpose;
    }

    public void setPurpose(Info purpose) {
        this.purpose = purpose;
    }
}
