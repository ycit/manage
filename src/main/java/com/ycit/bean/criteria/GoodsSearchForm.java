package com.ycit.bean.criteria;

import com.ycit.bean.modal.Goods;

/**
 * 商品查询表单
 *
 * @author xlch
 * @Date 2018-03-22 16:01
 */
public class GoodsSearchForm extends Goods {

    private Integer minPrice;
    private Integer maxPrice;
    private Integer minCapacity;
    private Integer maxCapacity;
    private Integer minVoltage;
    private Integer maxVoltage;
    private Integer minSaleCount;
    private Integer maxSaleCount;

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinCapacity() {
        return minCapacity;
    }

    public void setMinCapacity(Integer minCapacity) {
        this.minCapacity = minCapacity;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getMinVoltage() {
        return minVoltage;
    }

    public void setMinVoltage(Integer minVoltage) {
        this.minVoltage = minVoltage;
    }

    public Integer getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage(Integer maxVoltage) {
        this.maxVoltage = maxVoltage;
    }

    public Integer getMinSaleCount() {
        return minSaleCount;
    }

    public void setMinSaleCount(Integer minSaleCount) {
        this.minSaleCount = minSaleCount;
    }

    public Integer getMaxSaleCount() {
        return maxSaleCount;
    }

    public void setMaxSaleCount(Integer maxSaleCount) {
        this.maxSaleCount = maxSaleCount;
    }
}
