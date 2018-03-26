package com.ycit.bean.vo;

import com.ycit.bean.modal.Goods;
import com.ycit.bean.modal.dict.Info;
import org.springframework.beans.BeanUtils;

/**
 * 商品值对象(前端显示)
 *
 * @author xlch
 * @Date 2018-03-24 21:53
 */
public class GoodsVo {

    private int id;
    private Info store;
    private Info brand;
    private String name; //名称
    private String img; //商品主图
    private String thumbnail;  //商品缩略图
    private int price; //商品价格
    private int saleCount;
    private int stock;
    private String description;
    private int capacity; // 电池容量
    private Info category; // 电池种类
    private Info purpose; // 电池用途
    private int voltage; // 电压


    public static GoodsVo fromBean(Goods goods) {
        GoodsVo goodsVo = new GoodsVo();
        BeanUtils.copyProperties(goods, goodsVo);
        return goodsVo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Info getStore() {
        return store;
    }

    public void setStore(Info store) {
        this.store = store;
    }

    public Info getBrand() {
        return brand;
    }

    public void setBrand(Info brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }
}
