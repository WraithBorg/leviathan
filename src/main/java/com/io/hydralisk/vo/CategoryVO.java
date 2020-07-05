package com.io.hydralisk.vo;

import java.util.List;

public class CategoryVO {
    private Long catid;

    private String description;
    private Long ex_table_id;
    private String imgurl;
    private Integer orderindex;
    private Long pid;
    private Integer status;
    private String title;
    private List<CategoryVO> child;

    public Long getCatid() {
        return catid;
    }

    public void setCatid(Long catid) {
        this.catid = catid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEx_table_id() {
        return ex_table_id;
    }

    public void setEx_table_id(Long ex_table_id) {
        this.ex_table_id = ex_table_id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Integer getOrderindex() {
        return orderindex;
    }

    public void setOrderindex(Integer orderindex) {
        this.orderindex = orderindex;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CategoryVO> getChild() {
        return child;
    }

    public void setChild(List<CategoryVO> child) {
        this.child = child;
    }
//    public static List<CategoryVO> getDefaultList(){
//        List<CategoryVO> categoryVOS = new ArrayList<>();
//        CategoryVO 福鼎白茶 = new CategoryVO();
//        福鼎白茶.setCatid(21L);
//        福鼎白茶.setDescription("");
//        福鼎白茶.setEx_table_id(0L);
//        福鼎白茶.setImgurl("");
//        福鼎白茶.setOrderindex(0);
//        福鼎白茶.setPid(0L);
//        福鼎白茶.setStatus(1);
//        福鼎白茶.setTitle("福鼎白茶");
//        福鼎白茶.setChild(CategoryChildVO.get福鼎白茶Child());
//
//        CategoryVO 茶具 = new CategoryVO();
//        茶具.setCatid(26L);
//        茶具.setDescription("");
//        茶具.setEx_table_id(0L);
//        茶具.setImgurl("");
//        茶具.setOrderindex(0);
//        茶具.setPid(0L);
//        茶具.setStatus(1);
//        茶具.setTitle("茶具");
//        茶具.setChild(null);
//
//        CategoryVO 福鼎小吃 = new CategoryVO();
//        福鼎小吃.setCatid(27L);
//        福鼎小吃.setDescription("");
//        福鼎小吃.setEx_table_id(0L);
//        福鼎小吃.setImgurl("https://kfbc-deitui-com.oss-cn-hangzhou.aliyuncs.com/attach/2020/05/16/4a663dbd1e9a1c12c1b1b9c3e5e081b6.jpg");
//        福鼎小吃.setOrderindex(0);
//        福鼎小吃.setPid(0L);
//        福鼎小吃.setStatus(1);
//        福鼎小吃.setTitle("福鼎小吃");
//        福鼎小吃.setChild(null);
//
//        categoryVOS.add(福鼎白茶);
//        categoryVOS.add(茶具);
//        categoryVOS.add(福鼎小吃);
//        return categoryVOS;
//    }

}
