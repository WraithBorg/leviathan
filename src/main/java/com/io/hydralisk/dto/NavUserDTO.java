package com.io.hydralisk.dto;

import com.io.hydralisk.constant.CConstant;

public class NavUserDTO {
    private Long userid = 136L;
    private String username = "zxu";
    private String telephone = CConstant.DEFAULT_TELEPHONE;
    private String nickname = "zxu";
    private String money = "0.00";
    private Integer gold = 0;
    private Integer grade = 0;
    private String createtime = "2020-07-03 15:48:47";
    private String lasttime = "2020-07-03 15:48:47";
    private Integer user_type = 1;
    private Integer status = 10;
    private Integer is_auth = 0;
    private String user_head = CConstant.DEFAULT_HEAD_IMG;
    private Integer follow_num = 0;
    private Integer followed_num = 0;
    private Integer gender = 0;
    private Integer invite_userid = 0;
    private String birthday = "2018-11-03 09:33:01";

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIs_auth() {
        return is_auth;
    }

    public void setIs_auth(Integer is_auth) {
        this.is_auth = is_auth;
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }

    public Integer getFollow_num() {
        return follow_num;
    }

    public void setFollow_num(Integer follow_num) {
        this.follow_num = follow_num;
    }

    public Integer getFollowed_num() {
        return followed_num;
    }

    public void setFollowed_num(Integer followed_num) {
        this.followed_num = followed_num;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getInvite_userid() {
        return invite_userid;
    }

    public void setInvite_userid(Integer invite_userid) {
        this.invite_userid = invite_userid;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
