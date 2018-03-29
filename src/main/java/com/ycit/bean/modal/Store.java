package com.ycit.bean.modal;

import com.ycit.bean.base.Base;
import com.ycit.bean.entity.StoreForm;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 专卖店
 *
 * @author xlch
 * @Date 2018-03-27 16:53
 */
public class Store extends Base {

    private int id;
    private String name;
    private String img;
    private String tel;
    private String provinceId;
    private String provinceName;
    private String cityId;
    private String cityName;
    private String districtId;
    private String districtName;
    private String addressDetail;
    private List<StoreBrand> brands;

    public static Store fromBean(StoreForm storeForm) {
        Store store = new Store();
        BeanUtils.copyProperties(storeForm, store);
        return store;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public Store() {
    }

    public Store(String img) {
        this.img = img;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<StoreBrand> getBrands() {
        return brands;
    }

    public void setBrands(List<StoreBrand> brands) {
        this.brands = brands;
    }
}
