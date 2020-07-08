package com.io.hydralisk.service;

import com.io.hydralisk.domain.ItemInfo;
import com.io.hydralisk.domain.ItemInfoImg;
import com.io.hydralisk.mapper.ItemInfoImgMapper;
import com.io.hydralisk.mapper.ItemInfoMapper;
import com.io.hydralisk.service.usb.ItemInfoService;
import com.io.hydralisk.util.CCommonUtils;
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
        List<ItemInfoImg> itemInfoImgs = itemInfoImgMapper.selectByMap(CCommonUtils.ofMap(ItemInfoImg.t.item_id, id));
        if (itemInfoImgs.size() > 0) {
            itemInfo.setDefaultImg(itemInfoImgs.get(0).getUrl());
        }
        return itemInfo;
    }

    /**
     * 获取商品信息以及图片
     */
    @Override
    public List<ItemInfo> selectListWithImg() {
        List<ItemInfo> itemInfos = itemInfoMapper.selectList(null);
        itemInfos.forEach(m -> {
            List<ItemInfoImg> itemInfoImgs = itemInfoImgMapper.selectByMap(CCommonUtils.ofMap(ItemInfoImg.t.item_id, m.getId(), ItemInfoImg.t.default_flag, "1"));
            if (itemInfoImgs.size() > 0) {
                m.setDefaultImg(itemInfoImgs.get(0).getUrl());
            }
        });
        return itemInfos;
    }
    @Override
    public List<ItemInfo> selectListWithImg( List<ItemInfo> itemInfos) {

        itemInfos.forEach(m -> {
            List<ItemInfoImg> itemInfoImgs = itemInfoImgMapper.selectByMap(CCommonUtils.ofMap(ItemInfoImg.t.item_id, m.getId(), ItemInfoImg.t.default_flag, "1"));
            if (itemInfoImgs.size() > 0) {
                m.setDefaultImg(itemInfoImgs.get(0).getUrl());
            }
        });
        return itemInfos;
    }
}
