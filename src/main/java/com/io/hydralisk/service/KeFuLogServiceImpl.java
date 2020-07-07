package com.io.hydralisk.service;

import com.io.hydralisk.domain.KeFuLogInfo;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.mapper.KeFuLogMapper;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.service.usb.KeFuLogService;
import com.io.hydralisk.util.CCommonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KeFuLogServiceImpl implements KeFuLogService {
    @Resource
    private KeFuLogMapper keFuLogMapper;
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public List<KeFuLogInfo> getMyLog() {
        UserInfo defaultUser = userInfoMapper.getDefaultUser();
        List<KeFuLogInfo> keFuLogInfos = keFuLogMapper.selectByMap(CCommonUtils.ofMap(KeFuLogInfo.t.user_id, defaultUser.getId()));
        return keFuLogInfos;
    }
}
