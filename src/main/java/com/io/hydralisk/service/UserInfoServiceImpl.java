package com.io.hydralisk.service;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.service.usb.UserInfoService;
import com.io.hydralisk.util.CommonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Override
    public UserInfo getDefaultUser(){
        List<UserInfo> telephone = userInfoMapper.selectByMap(CommonUtils.ofMap("telephone", CConstant.DEFAULT_TELEPHONE));
        UserInfo userInfo = telephone.get(0);
        return userInfo;
    }
}
