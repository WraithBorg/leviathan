package com.io.hydralisk.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_info")
public class UserInfo {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    @TableField(value = t.nickname)
    private String nickName;
    @TableField(value = t.telephone)
    private String telePhone;
    @TableField(value = t.password)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {

        this.telePhone = telePhone;
    }
    public static final class t{
        public static final String nickname = "nickname";
        public static final String telephone = "telephone";
        public static final String password = "password";

    }
}
