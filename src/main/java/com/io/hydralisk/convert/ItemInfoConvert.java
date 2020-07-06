package com.io.hydralisk.convert;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.domain.ItemInfo;
import com.io.hydralisk.domain.ShopCartItemInfo;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.mapper.ShopCartItemMapper;
import com.io.hydralisk.service.usb.UserInfoService;
import com.io.hydralisk.util.DDateUtil;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.util.DDecimalUtil;
import com.io.hydralisk.vo.ItemInfoVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品信息
 */
@Component
public class ItemInfoConvert {
    @Resource
    private ShopCartItemMapper shopCartItemMapper;
    @Resource
    private UserInfoService userInfoService;

    public List<ItemInfoVO> getItemInfoVOS(List<ItemInfo> itemInfos) {
        List<ItemInfoVO> itemInfoVOS = itemInfos.stream().map(m -> getItemInfoVO(m)).collect(Collectors.toList());
        return itemInfoVOS;
    }

    public ItemInfoVO getItemInfoVO(ItemInfo itemInfo) {
        UserInfo defaultUser = userInfoService.getDefaultUser();
        ShopCartItemInfo cartItemInfo = shopCartItemMapper.getByUserItem(itemInfo.getId(), defaultUser.getId());
        Integer cartAmount;
        if (cartItemInfo == null){
            cartAmount = 0;
        }else {
            cartAmount = DDecimalUtil.toInt(cartItemInfo.getAmount());
        }
        //
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setBuy_num(itemInfo.getBuyNum());
        itemInfoVO.setCart_amount(cartAmount);
        itemInfoVO.setCatid(itemInfo.getCategoryId());
        itemInfoVO.setCreatetime(DDateUtil.format(new Date()));
        itemInfoVO.setDescription(itemInfo.getName());
        itemInfoVO.setId(itemInfo.getId());
        itemInfoVO.setImgsdata(itemInfo.getDefaultImg());
        if (CCommonUtils.isNotBlank(itemInfo.getDefaultImg())) {
            itemInfoVO.setImgurl(CConstant.IMAGE_HOST + itemInfo.getDefaultImg());
        }

        itemInfoVO.setIncart(cartAmount > 0 ? 1 : 0);
        itemInfoVO.setPrice(itemInfo.getPrice().toEngineeringString());
        itemInfoVO.setTitle(itemInfo.getName());
        itemInfoVO.setWeight(itemInfo.getWeight().toEngineeringString());
        itemInfoVO.setContent(itemInfo.getContent());
        // DEFAULT
        itemInfoVO.setEtime(0);
        itemInfoVO.setEx_table_data_id(0);
        itemInfoVO.setEx_table_id(0);
        itemInfoVO.setIshot(1);
        itemInfoVO.setIsksid(0);
        itemInfoVO.setFav_num(1);
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
        itemInfoVO.setPt_price("0");
        itemInfoVO.setStatus(1);
        itemInfoVO.setStime(0);
        itemInfoVO.setTotal_num(99);
        itemInfoVO.setUpdatetime(DDateUtil.format(new Date()));
        itemInfoVO.setVideourl("");
        itemInfoVO.setView_num(550);

        return itemInfoVO;
    }
}
