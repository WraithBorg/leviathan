package com.io.hydralisk.service.usb;

import com.io.hydralisk.domain.ItemInfo;

import java.util.List;

public interface ItemInfoService {
    ItemInfo getItemWithImg(String id);

    List<ItemInfo> selectListWithImg();

    public List<ItemInfo> selectListWithImg(List<ItemInfo> itemInfos);
}
