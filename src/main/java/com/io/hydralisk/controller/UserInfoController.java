package com.io.hydralisk.controller;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.convert.UserInfoConvert;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.dto.ModifyPassDTO;
import com.io.hydralisk.dto.UserInfoDTO;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.service.usb.UserInfoService;
import com.io.hydralisk.util.CommonUtils;
import com.io.hydralisk.vo.ResultVO;
import com.io.hydralisk.vo.UserInfoVO;
import com.io.hydralisk.vo.UserPassVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private UserInfoConvert userInfoConvert;

    /**
     * @Range 用户设置
     */
    @RequestMapping("/set")
    public Object set() {
        UserInfo defaultUser = userInfoService.getDefaultUser();
        UserInfoVO userInfoVO = userInfoConvert.getUserVO(defaultUser);
        Map data = CommonUtils.ofMap("data", userInfoVO);
        return new ResultVO(data, CConstant.WEB_HOST + "/h5/pages/user/set");
    }

    /**
     * 获取用户资料
     *
     * @Range 修改用户资料
     */
    @RequestMapping("/info")
    public Object info() {
        UserInfo defaultUser = userInfoService.getDefaultUser();
        UserInfoVO userInfoVO = userInfoConvert.getUserVO(defaultUser);
        Map data = CommonUtils.ofMap("data", userInfoVO);
        return new ResultVO(data, CConstant.WEB_HOST + "/h5/pages/user/info");
    }

    /**
     * 修改用户资料
     */
    @PostMapping("/save")
    public Object save(UserInfoDTO userInfoDTO) {
        UserInfo defaultUser = userInfoService.getDefaultUser();
        defaultUser.setNickName(userInfoDTO.getNickname());
        userInfoMapper.updateById(defaultUser);

        return new ResultVO(new ArrayList<>(), CConstant.WEB_HOST + "/h5/pages/user/info");
    }

    /**
     * 获取用户资料
     *
     * @Range 修改密码
     */
    @GetMapping("/password")
    public Object password() {
        UserInfo defaultUser = userInfoService.getDefaultUser();
        UserPassVO userPassVO = userInfoConvert.getUserPassVO(defaultUser);
        Map data = CommonUtils.ofMap("data", userPassVO);
        return new ResultVO(data, CConstant.WEB_HOST + "/h5/pages/user/password");
    }

    /**
     * 修改密码
     */
    @PostMapping("/passwordsave")
    public Object passwordsave(ModifyPassDTO modifyPassDTO) {
        // validate
        if (!modifyPassDTO.getPassword().equals(modifyPassDTO.getPassword2())){
            return new ResultVO(1, "新密码输入不一致",new ArrayList<>(),CConstant.WEB_HOST + "/h5/pages/user/password");
        }

        UserInfo defaultUser = userInfoService.getDefaultUser();
        if (!defaultUser.getPassword().equals(modifyPassDTO.getOldpassword())){
            return new ResultVO(1, "旧密码出错",new ArrayList<>(),CConstant.WEB_HOST + "/h5/pages/user/password");
        }
        // save
        defaultUser.setPassword(modifyPassDTO.getPassword());
        userInfoMapper.updateById(defaultUser);
        UserPassVO userPassVO = userInfoConvert.getUserPassVO(defaultUser);
        Map data = CommonUtils.ofMap("data", userPassVO);
        return new ResultVO(data, CConstant.WEB_HOST + "/h5/pages/user/password");
    }

}
