package com.io.hydralisk.controller;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.convert.UserInfoConvert;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.dto.NavDTO;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.service.usb.UserInfoService;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.vo.UserInfoVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("/module")
@RestController
public class ModuleController {

    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private UserInfoConvert userInfoConvert;

    /**
     * 登录后获取用户信息
     */
    @RequestMapping("/b2c_user")
    public Object b2c_user() {
        UserInfo defaultUser = userInfoService.getDefaultUser();
        UserInfoVO userInfoVO = userInfoConvert.getUserVO(defaultUser);
        Map data = CCommonUtils.ofMap("data", userInfoVO, "navList", NavDTO.getNavList());
        Map ofMap = CCommonUtils.ofMap("error", 0, "message", "success", "data",data, "url", CConstant.WEB_HOST + "/h5/pageb2c/b2c_user/index");
        return ofMap;
    }


}
