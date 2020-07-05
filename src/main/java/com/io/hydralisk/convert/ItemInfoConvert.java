package com.io.hydralisk.convert;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.domain.ItemInfo;
import com.io.hydralisk.util.CommonDateUtil;
import com.io.hydralisk.vo.ItemInfoVO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemInfoConvert {
    public List<ItemInfoVO> getItemInfoVOS(List<ItemInfo> itemInfos) {
        List<ItemInfoVO> itemInfoVOS = itemInfos.stream().map(m -> getItemInfoVO(m)).collect(Collectors.toList());
        return itemInfoVOS;
    }

    public ItemInfoVO getItemInfoVO(ItemInfo itemInfo) {
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setBuy_num(itemInfo.getBuyNum());
        itemInfoVO.setCart_amount(0);//TODO
        itemInfoVO.setCatid(itemInfo.getCategoryId());
        itemInfoVO.setCreatetime(CommonDateUtil.getDateFormat().format(new Date()));
        itemInfoVO.setDescription(itemInfo.getName());
        itemInfoVO.setId(itemInfo.getId());
        itemInfoVO.setImgsdata(itemInfo.getImgUrl());
        itemInfoVO.setImgurl(CConstant.IMAGE_HOST+itemInfo.getImgUrl());
        itemInfoVO.setIncart(0);//TODO
        itemInfoVO.setPrice(itemInfo.getPrice().toEngineeringString());
        itemInfoVO.setTitle(itemInfo.getName());
        itemInfoVO.setWeight(itemInfo.getWeight().toEngineeringString());
        // DEFAULT
        itemInfoVO.setEtime(0);
        itemInfoVO.setEx_table_data_id(0);
        itemInfoVO.setEx_table_id(0);
        itemInfoVO.setIshot(1);
        itemInfoVO.setIsksid(0);
        itemInfoVO.setIsnew(1);
        itemInfoVO.setIsrecommend(1);
        itemInfoVO.setKs_label_name("");
        itemInfoVO.setKs_label_size("");
        itemInfoVO.setLower_price("0");
        itemInfoVO.setMarket_price("0");
        itemInfoVO.setMonth_buy_num(0);
        itemInfoVO.setOtype("");
        itemInfoVO.setPt_min(0);
        itemInfoVO.setPt_open(0);
        itemInfoVO.setPrice("0");
        itemInfoVO.setStatus(1);
        itemInfoVO.setTotal_num(99);
        itemInfoVO.setUpdatetime(CommonDateUtil.getDateFormat().format(new Date()));
        itemInfoVO.setVideourl("");
        itemInfoVO.setView_num(550);

        return itemInfoVO;
    }
}