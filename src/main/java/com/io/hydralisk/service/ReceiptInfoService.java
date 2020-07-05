package com.io.hydralisk.service;

import com.io.hydralisk.domain.ReceiptInfo;

import java.util.List;

public interface ReceiptInfoService {
    public ReceiptInfo selectById(String id);

    void insert(ReceiptInfo receiptInfo);

    List<ReceiptInfo> selectList(Object o);

    public void updateById(ReceiptInfo receiptInfo);

    public void deleteById(String id);
}
