package com.io.hydralisk.controller;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.dto.LoginDTO;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户登录
 */
@RequestMapping("/login")
@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 用户登录
     */
    @PostMapping("/loginsave")
    public Object loginsave(LoginDTO loginDTO) {
        // validate
        String telephone = loginDTO.getTelephone();
        List<UserInfo> v_telephone = userInfoMapper.selectByMap(CommonUtils.ofMap("telephone", telephone));
        if (v_telephone.size() == 0) {
            return CommonUtils.ofMap("error", 1, "message", "账号不存在", "data", "", "url", CConstant.WEB_HOST+"/h5/pages/login/index");
        }
        UserInfo userInfo = v_telephone.get(0);
        if (!userInfo.getPassword().equals(loginDTO.getPassword())) {
            return CommonUtils.ofMap("error", 1, "message", "密码不正确", "data", "", "url", CConstant.WEB_HOST+"/h5/pages/login/index");
        }
        //
        Map<String, String> data = CommonUtils.ofMap("authcode", "12JTddcCbJ6T2Iby6d3SeUcy5M8i5U5z2Q1TdEzNiUyQyUyMnAlMjIlM0ElMjI0ODE2ZWIlMjIlMkMlMjJhJTIyJTNBJTIyNWViYiUyMiUyQyUyMmUlMjIlM0ExNTk0MDQzMDcyJTdE", "authcodeLong", "12JTddcCbJ6T2Iby6d3SeUcy5M8i5U5z2Q1TdEzNiUyQyUyMnAlMjIlM0ElMjI0ODE2ZWIlMjIlMkMlMjJhJTIyJTNBJTIyNWViYiUyMiUyQyUyMmUlMjIlM0ExNTk0MDQzMDcyJTJDJTIyZWwlMjIlM0ExNjE5NzkwMjcyJTdE", "backurl", "\\/index.php");
        return CommonUtils.ofMap("error", 0, "message", "登陆成功", "data", data, "url", "/index.php");

    }

    @RequestMapping("/findpwdSave")
    public Object findpwdSave() {

        return CommonUtils.ofMap("error", 0, "message", "密码修改成功，马上登录", "data", 0, "url:", "/index.php?m=login");
    }
}
