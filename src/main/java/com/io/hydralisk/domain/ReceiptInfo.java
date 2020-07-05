package com.io.hydralisk.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("receipt_info")
public class ReceiptInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "user_id")
    private Long userId;
    @TableField(value = "address")
    private String address;
    @TableField(value = "zip_code")
    private String zipCode;
    @TableField(value = "true_name")
    private String trueName;
    @TableField(value = "telephone")
    private String telephone;
    @TableField(value = "province_id")
    private Integer provinceId;
    @TableField(value = "city_id")
    private Integer cityId;
    @TableField(value = "town_id")
    private Integer townId;
    @TableField(value = "default_flag")
    private Integer defaultFlag;

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    public Integer getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Integer defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
