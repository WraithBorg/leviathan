package com.io.hydralisk.test;

import com.alibaba.fastjson.JSON;
import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.dto.NavDTO;
import com.io.hydralisk.dto.NavUserDTO;
import com.io.hydralisk.util.CCommonUtils;

import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map data = CCommonUtils.ofMap("data", new NavUserDTO(), "navList", NavDTO.getNavList());
        Map ofMap = CCommonUtils.ofMap("error", 0, "message", "success", "data", data, "url", CConstant.WEB_HOST+"/h5/pageb2c/b2c_user/index");
        Object o = JSON.toJSON(ofMap);
        System.out.println(o);
    }
}
