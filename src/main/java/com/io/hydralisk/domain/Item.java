package com.io.hydralisk.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

@TableName(value = "item")
public class Item {
    @TableId
    private Long id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "img_url")
    private String imgUrl;
    @TableField(value = "price")
    private BigDecimal price;
    @TableField(value = "weight")
    private BigDecimal weight;
    @TableField(value = "category_id")
    private Long categoryId;
}
