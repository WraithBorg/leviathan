package com.io.hydralisk.dto;

import java.util.ArrayList;
import java.util.List;

public class NavChildDTO {
    private Long id;
    private String title;
    private Integer orderindex;
    private String link_url;
    private String target;
    private Integer pid;
    private Integer group_id;
    private String m;
    private String a;
    private Integer status;
    private String logo;
    private String icon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrderindex() {
        return orderindex;
    }

    public void setOrderindex(Integer orderindex) {
        this.orderindex = orderindex;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    static NavChildDTO get我的消息() {
        NavChildDTO 我的消息 = new NavChildDTO();
        我的消息.setA("");
        我的消息.setGroup_id(7);
        我的消息.setIcon("icon-notice");
        我的消息.setId(298L);
        我的消息.setLink_url("/index.php?m=notice&amp;a=my");
        我的消息.setLogo("");
        我的消息.setM("");
        我的消息.setOrderindex(0);
        我的消息.setPid(297);
        我的消息.setStatus(1);
        我的消息.setTarget("");
        我的消息.setTitle("我的消息");
        return 我的消息;
    }

    static NavChildDTO get消费记录() {
        NavChildDTO 消费记录 = new NavChildDTO();
        消费记录.setA("my");
        消费记录.setGroup_id(7);
        消费记录.setIcon("icon-moneybag");
        消费记录.setId(299L);
        消费记录.setLink_url("/index.php?m=pay_log&amp;a=my");
        消费记录.setLogo("");
        消费记录.setM("pay_log");
        消费记录.setOrderindex(0);
        消费记录.setPid(297);
        消费记录.setStatus(1);
        消费记录.setTarget("");
        消费记录.setTitle("消费记录");
        return 消费记录;
    }

    static NavChildDTO get充值记录() {
        NavChildDTO 充值记录 = new NavChildDTO();
        充值记录.setA("my");
        充值记录.setGroup_id(7);
        充值记录.setIcon("icon-moneybag");
        充值记录.setId(300L);
        充值记录.setLink_url("/index.php?m=recharge&amp;a=my");
        充值记录.setLogo("");
        充值记录.setM("recharge");
        充值记录.setOrderindex(0);
        充值记录.setPid(297);
        充值记录.setStatus(1);
        充值记录.setTarget("");
        充值记录.setTitle("充值记录");
        return 充值记录;
    }

    static NavChildDTO get我的收藏() {
        NavChildDTO 我的收藏 = new NavChildDTO();
        我的收藏.setA("my");
        我的收藏.setGroup_id(7);
        我的收藏.setIcon("icon-favor");
        我的收藏.setId(301L);
        我的收藏.setLink_url("/index.php?m=recharge&amp;a=my");
        我的收藏.setLogo("");
        我的收藏.setM("fav");
        我的收藏.setOrderindex(0);
        我的收藏.setPid(297);
        我的收藏.setStatus(1);
        我的收藏.setTarget("");
        我的收藏.setTitle("我的收藏");
        return 我的收藏;
    }

    static NavChildDTO get我的评论() {
        NavChildDTO 我的评论 = new NavChildDTO();
        我的评论.setA("my");
        我的评论.setGroup_id(7);
        我的评论.setIcon("icon-comment");
        我的评论.setId(302L);
        我的评论.setLink_url("/index.php?m=comment&amp;a=my&amp;tablename=article");
        我的评论.setLogo("");
        我的评论.setM("comment");
        我的评论.setOrderindex(0);
        我的评论.setPid(297);
        我的评论.setStatus(1);
        我的评论.setTarget("");
        我的评论.setTitle("我的评论");
        return 我的评论;
    }

    static NavChildDTO get收货地址() {
        NavChildDTO 收货地址 = new NavChildDTO();
        收货地址.setA("my");
        收货地址.setGroup_id(7);
        收货地址.setIcon("icon-addressbook");
        收货地址.setId(303L);
        收货地址.setLink_url("/index.php?m=user_address&amp;a=my");
        收货地址.setLogo("");
        收货地址.setM("user_address");
        收货地址.setOrderindex(0);
        收货地址.setPid(297);
        收货地址.setStatus(1);
        收货地址.setTarget("");
        收货地址.setTitle("收货地址");
        return 收货地址;
    }

    static List<NavChildDTO> getNavList() {
        List<NavChildDTO> navChildDTOS = new ArrayList<>();
        navChildDTOS.add(get我的消息());
        navChildDTOS.add(get消费记录());
        navChildDTOS.add(get充值记录());
        navChildDTOS.add(get我的收藏());
        navChildDTOS.add(get我的评论());
        navChildDTOS.add(get收货地址());
        return navChildDTOS;


    }
}
