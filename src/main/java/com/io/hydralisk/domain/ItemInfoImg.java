package com.io.hydralisk.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("item_img")
public class ItemInfoImg {
    @TableId
    private Long id;
    @TableField(value = "item_id")
    private Long itemId;
    @TableField(value = "default_flag")
    private Boolean defaultFlag;
    @TableField(value = "url")
    private String url;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Boolean getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
