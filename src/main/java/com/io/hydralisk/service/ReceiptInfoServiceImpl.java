package com.io.hydralisk.service;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.domain.ReceiptInfo;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.mapper.ReceiptInfoMapper;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.service.usb.ReceiptInfoService;
import com.io.hydralisk.service.usb.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ReceiptInfoServiceImpl implements ReceiptInfoService {
    @Resource
    private ReceiptInfoMapper receiptInfoMapper;
    @Resource
    private UserInfoService userInfoService;

    @Override
    public ReceiptInfo selectById(String id) {
        ReceiptInfo receiptInfo = receiptInfoMapper.selectById(id);
        return receiptInfo;
    }

    @Override
    public void insert(ReceiptInfo receiptInfo) {
        UserInfo defaultUser = userInfoService.getDefaultUser();
        receiptInfo.setUserId(defaultUser.getId());
        receiptInfoMapper.insert(receiptInfo);
    }

    @Override
    public void deleteById(String id) {
        receiptInfoMapper.deleteById(id);
    }

    @Override
    public void updateById(ReceiptInfo receiptInfo) {
        receiptInfoMapper.updateById(receiptInfo);
    }

    @Override
    public List<ReceiptInfo> selectList(Object o) {
        return receiptInfoMapper.selectList(null);
    }
}
