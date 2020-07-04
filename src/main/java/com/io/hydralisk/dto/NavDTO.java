package com.io.hydralisk.dto;

import java.util.ArrayList;
import java.util.List;

public class NavDTO {
    private String a;
    private List<NavChildDTO> child;
    private Integer group_id;
    private String icon;
    private Long id;
    private String link_url;
    private String logo;
    private String m;
    private Integer orderindex;
    private Integer pid;
    private Integer status;
    private String target;
    private String title;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public List<NavChildDTO> getChild() {
        return child;
    }

    public void setChild(List<NavChildDTO> child) {
        this.child = child;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public Integer getOrderindex() {
        return orderindex;
    }

    public void setOrderindex(Integer orderindex) {
        this.orderindex = orderindex;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private static NavDTO get栏目二() {
        NavDTO navDTO = new NavDTO();
        navDTO.setA("");
        navDTO.setChild(NavChildDTO.getNavList());
        navDTO.setGroup_id(7);
        navDTO.setIcon("");
        navDTO.setId(297L);
        navDTO.setLink_url("");
        navDTO.setLogo("");
        navDTO.setM("");
        navDTO.setOrderindex(0);
        navDTO.setPid(0);
        navDTO.setStatus(1);
        navDTO.setTarget("_self");
        navDTO.setTitle("栏目二");
        return navDTO;
    }

    public static  List<NavDTO> getNavList() {
        List<NavDTO> navDTOS = new ArrayList<>();
        navDTOS.add(get栏目二());
        return navDTOS;


    }
}
