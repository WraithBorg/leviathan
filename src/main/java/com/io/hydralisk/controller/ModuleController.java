package com.io.hydralisk.controller;

import com.alibaba.fastjson.JSON;
import com.io.hydralisk.dto.NavDTO;
import com.io.hydralisk.util.CommonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("module.php?m=b2c_user")
public class ModuleController {

    @RequestMapping
    public Object b2c_user(String authcode) {

        Map ofMap = CommonUtils.ofMap("error", 0, "message", "success", "data", NavDTO.getNavList(), "url", "http://localhost:8080/h5/pageb2c/b2c_user/index");

        return ofMap;
    }


}
