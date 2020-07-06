package com.io.hydralisk.vo;

import com.io.hydralisk.constant.CConstant;

import java.util.Date;

public class ReceiptInfoVO {
    private Long id;
    private Long userid;
    private String address;
    private String telephone;
    private String truename;
    private Integer status;
    private String zip_code;
    private Integer lastid;
    private Integer province_id;
    private Integer city_id;
    private Integer town_id;
    private Integer isdefault;
    private Date dateline;
    private String pct_address;
    private String lat;
    private String lng;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public Integer getLastid() {
        return lastid;
    }

    public void setLastid(Integer lastid) {
        this.lastid = lastid;
    }

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public Integer getTown_id() {
        return town_id;
    }

    public void setTown_id(Integer town_id) {
        this.town_id = town_id;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public Date getDateline() {
        return dateline;
    }

    public void setDateline(Date dateline) {
        this.dateline = dateline;
    }

    public String getPct_address() {
        return pct_address;
    }

    public void setPct_address(String pct_address) {
        this.pct_address = pct_address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public static ReceiptInfoVO getDefault() {
        ReceiptInfoVO receiptInfoVO = new ReceiptInfoVO();
        receiptInfoVO.setId(55L);
        receiptInfoVO.setUserid(136L);
        receiptInfoVO.setAddress("九号楼");
        receiptInfoVO.setTelephone(CConstant.DEFAULT_TELEPHONE);
        receiptInfoVO.setTruename("张三");
        receiptInfoVO.setStatus(2);
        receiptInfoVO.setZip_code("");
        receiptInfoVO.setLastid(0);
        receiptInfoVO.setProvince_id(1);
        receiptInfoVO.setCity_id(37);
        receiptInfoVO.setTown_id(567);
        receiptInfoVO.setIsdefault(0);
        receiptInfoVO.setDateline(new Date());
        receiptInfoVO.setPct_address("北京市东城区东华门街道11号楼");
        receiptInfoVO.setLat("39.918922");
        receiptInfoVO.setLng("116.412224");
        return receiptInfoVO;

    }
}
