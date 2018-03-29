package com.ycit.bean.modal;

import com.ycit.bean.base.Base;

/**
 * 资讯
 *
 * @author xlch
 * @Date 2018-03-28 17:10
 */
public class News extends Base {

    private int id;
    private String img;
    private String url;

    public News() {
    }

    public News(String img, String url) {
        this.img = img;
        this.url = url;
    }

    public News(int id, String img, String url) {
        this.id = id;
        this.img = img;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
