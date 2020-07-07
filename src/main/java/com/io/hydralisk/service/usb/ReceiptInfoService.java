package com.io.hydralisk.service.usb;

import com.io.hydralisk.domain.ReceiptInfo;

import java.util.List;

public interface ReceiptInfoService {
    public ReceiptInfo selectById(String id);

    void insert(ReceiptInfo receiptInfo);

    List<ReceiptInfo> selectList(String userId);

    public void updateById(ReceiptInfo receiptInfo);

    public void deleteById(String id);
}
