//package com.io.hydralisk.vo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CategoryChildVO {
//    private Long catid;
//    private String description;
//    private Long  ex_table_id;
//    private String imgurl;
//    private Integer orderindex;
//    private Long pid;
//    private Integer status;
//    private String  title;
//
//    public Long getCatid() {
//        return catid;
//    }
//
//    public void setCatid(Long catid) {
//        this.catid = catid;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Long getEx_table_id() {
//        return ex_table_id;
//    }
//
//    public void setEx_table_id(Long ex_table_id) {
//        this.ex_table_id = ex_table_id;
//    }
//
//    public String getImgurl() {
//        return imgurl;
//    }
//
//    public void setImgurl(String imgurl) {
//        this.imgurl = imgurl;
//    }
//
//    public Integer getOrderindex() {
//        return orderindex;
//    }
//
//    public void setOrderindex(Integer orderindex) {
//        this.orderindex = orderindex;
//    }
//
//    public Long getPid() {
//        return pid;
//    }
//
//    public void setPid(Long pid) {
//        this.pid = pid;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//    public static List<CategoryChildVO> get福鼎白茶Child(){
//        List<CategoryChildVO> 福鼎白茶Child = new ArrayList<>();
//        CategoryChildVO 白毫银针 = new CategoryChildVO();
//        白毫银针.setCatid(22L);
//        白毫银针.setDescription("白毫银针");
//        白毫银针.setEx_table_id(0L);
//        白毫银针.setImgurl("https://kfbc-deitui-com.oss-cn-hangzhou.aliyuncs.com/attach/2020/05/01/75999da99380e67afc3fcade31f475d4.jpg");
//        白毫银针.setOrderindex(0);
//        白毫银针.setPid(21L);
//        白毫银针.setStatus(1);
//        白毫银针.setTitle("白毫银针");
//
//        CategoryChildVO 白牡丹 = new CategoryChildVO();
//        白牡丹.setCatid(23L);
//        白牡丹.setDescription("白牡丹");
//        白牡丹.setEx_table_id(0L);
//        白牡丹.setImgurl("https://kfbc-deitui-com.oss-cn-hangzhou.aliyuncs.com/attach/2020/05/01/baa60e064a502842b2a5fdabb6c52567.jpg");
//        白牡丹.setOrderindex(0);
//        白牡丹.setPid(21L);
//        白牡丹.setStatus(1);
//        白牡丹.setTitle("白牡丹");
//
//        CategoryChildVO 寿眉 = new CategoryChildVO();
//        寿眉.setCatid(25L);
//        寿眉.setDescription("寿眉");
//        寿眉.setEx_table_id(0L);
//        寿眉.setImgurl("https://kfbc-deitui-com.oss-cn-hangzhou.aliyuncs.com/attach/2020/05/01/e154c74fb2cb59805d85407e294f1a03.jpg");
//        寿眉.setOrderindex(0);
//        寿眉.setPid(21L);
//        寿眉.setStatus(1);
//        寿眉.setTitle("寿眉");
//
//
//
//        福鼎白茶Child.add(白毫银针);
//        福鼎白茶Child.add(白牡丹);
//        福鼎白茶Child.add(寿眉);
//        return 福鼎白茶Child;
//
//    }
//
//}
