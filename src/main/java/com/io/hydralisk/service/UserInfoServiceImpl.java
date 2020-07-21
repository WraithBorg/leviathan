package com.io.hydralisk.service;

import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.service.usb.UserInfoService;
import com.io.hydralisk.util.CCommonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

//    @Override
//    public UserInfo getDefaultUser() {
//        return userInfoMapper.getDefaultUser();
//    }

    @Override
    public UserInfo getUserByTelephone(String telePhone) {
        List<UserInfo> userInfos = userInfoMapper.selectByMap(CCommonUtils.ofMap(UserInfo.t.telephone, telePhone));
        if (userInfos.size() == 0){
            return null;
        }
        return userInfos.get(0);
    }
}
