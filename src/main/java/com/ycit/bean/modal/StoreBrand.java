package com.ycit.bean.modal;

import com.ycit.bean.base.Base;

/**
 * 专卖店主营品牌
 *
 * @author xlch
 * @Date 2018-03-29 8:58
 */
public class StoreBrand extends Base {

    private int id;
    private int storeId;
    private int brandId;
    private String brandName;

    public StoreBrand() {
    }

    public StoreBrand(int storeId, int brandId, String brandName) {
        this.storeId = storeId;
        this.brandId = brandId;
        this.brandName = brandName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
