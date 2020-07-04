package com.io.hydralisk.controller;

import com.io.hydralisk.dto.LoginDTO;
import com.io.hydralisk.util.CommonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/login")
@RestController
public class LoginController {

    @RequestMapping("/loginsave")
    public Object loginsave(LoginDTO loginDTO){
        if (false){
            return CommonUtils.ofMap("error", 1, "message", "账号不存在", "data", "", "url", "http://localhost:8080/h5/pages/login/index");
        }
        Map<String, String> data = CommonUtils.ofMap("authcode", "12JTddcCbJ6T2Iby6d3SeUcy5M8i5U5z2Q1TdEzNiUyQyUyMnAlMjIlM0ElMjI0ODE2ZWIlMjIlMkMlMjJhJTIyJTNBJTIyNWViYiUyMiUyQyUyMmUlMjIlM0ExNTk0MDQzMDcyJTdE", "authcodeLong", "12JTddcCbJ6T2Iby6d3SeUcy5M8i5U5z2Q1TdEzNiUyQyUyMnAlMjIlM0ElMjI0ODE2ZWIlMjIlMkMlMjJhJTIyJTNBJTIyNWViYiUyMiUyQyUyMmUlMjIlM0ExNTk0MDQzMDcyJTJDJTIyZWwlMjIlM0ExNjE5NzkwMjcyJTdE", "backurl", "\\/index.php");
        return CommonUtils.ofMap("error", 0, "message", "登陆成功", "data", data, "url", "/index.php");

    }
    @RequestMapping("/findpwdSave")
    public Object findpwdSave(){

        return CommonUtils.ofMap("error", 0, "message", "密码修改成功，马上登录", "data", 0, "url:", "/index.php?m=login");
    }
}
