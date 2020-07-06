package com.io.hydralisk.service.usb;

import com.io.hydralisk.domain.ShopCartItemInfo;

import java.util.List;

public interface ShopCartItemService {
    List<ShopCartItemInfo> getShopCartInfo(String userId);

    void addItem(String userId,String productid, String amount);
}
