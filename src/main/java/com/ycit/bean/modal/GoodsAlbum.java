package com.ycit.bean.modal;

import com.ycit.bean.base.Base;

/**
 * 商品相册
 *
 * @author xlch
 * @Date 2018-03-26 9:50
 */
public class GoodsAlbum extends Base {

    private int id;
    private int goodsId;
    private String img;
    private String thumbnail;

    public GoodsAlbum() {
    }

    public GoodsAlbum(int goodsId, String img, String thumbnail) {
        this.goodsId = goodsId;
        this.img = img;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
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
}
