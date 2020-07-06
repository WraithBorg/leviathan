package com.io.hydralisk.service;

import com.io.hydralisk.domain.ItemInfo;
import com.io.hydralisk.domain.ItemInfoImg;
import com.io.hydralisk.mapper.ItemInfoImgMapper;
import com.io.hydralisk.mapper.ItemInfoMapper;
import com.io.hydralisk.service.usb.ItemInfoService;
import com.io.hydralisk.util.CommonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemInfoServiceImpl implements ItemInfoService {
    @Resource
    private ItemInfoMapper itemInfoMapper;
    @Resource
    private ItemInfoImgMapper itemInfoImgMapper;

    /**
     * 查询品项和默认图片
     * @param id
     * @return
     */
    @Override
    public ItemInfo getItemWithImg(String id) {
        ItemInfo itemInfo = itemInfoMapper.selectById(id);
        List<ItemInfoImg> itemInfoImgs = itemInfoImgMapper.selectByMap(CommonUtils.ofMap("item_id", id));
        if (itemInfoImgs.size() > 0) {
            itemInfo.setDefaultImg(itemInfoImgs.get(0).getUrl());
        }
        return itemInfo;
    }
}
