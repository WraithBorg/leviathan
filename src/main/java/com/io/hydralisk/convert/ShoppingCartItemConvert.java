package com.io.hydralisk.convert;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.domain.ShopCartItemInfo;
import com.io.hydralisk.util.DDateUtil;
import com.io.hydralisk.util.DDecimalUtil;
import com.io.hydralisk.vo.ShopCartItemVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShoppingCartItemConvert {


    public ShopCartItemVO getShoppingCartItemVO(ShopCartItemInfo info) {
        ShopCartItemVO vo = new ShopCartItemVO();
        vo.setAmount(DDecimalUtil.toInt(info.getAmount()));
        vo.setCreatetime(DDateUtil.format(info.getCreateTime()));
        vo.setEtime(0);
        vo.setId(info.getId());
        vo.setImgurl(CConstant.IMAGE_HOST + info.getItemImgUrl());
        vo.setKs_title("");
        vo.setKsid(0L);
        vo.setOtype("");
        vo.setPrice(DDecimalUtil.format(info.getPrice()));
        vo.setProductid(info.getItemId());
        vo.setPt_price("0");
        vo.setStime(0);
        vo.setTitle(info.getTitle());
        vo.setTotal_num(1000);//库存量
        vo.setUserid(info.getUserId());
        vo.setWeight(null);
        return vo;
    }

    public List<ShopCartItemVO> getShoppingCartItemVOS(List<ShopCartItemInfo> cartItemInfos) {
        List<ShopCartItemVO> shopCartItemVOS = cartItemInfos.stream().map(m -> getShoppingCartItemVO(m)).collect(Collectors.toList());
        return shopCartItemVOS;
    }
}
