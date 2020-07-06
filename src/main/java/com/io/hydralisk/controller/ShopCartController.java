package com.io.hydralisk.controller;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.convert.ShoppingCartItemConvert;
import com.io.hydralisk.domain.ShopCartItemInfo;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.mapper.ShopCartItemMapper;
import com.io.hydralisk.service.usb.ShopCartItemService;
import com.io.hydralisk.service.usb.UserInfoService;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.util.DDecimalUtil;
import com.io.hydralisk.vo.ResultVO;
import com.io.hydralisk.vo.ShopCartItemVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 购物车
 */
@RequestMapping("/shoppingcart")
@RestController
public class ShopCartController {
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private ShopCartItemService shopCartItemService;
    @Resource
    private ShoppingCartItemConvert cartItemConvert;

    /**
     * 获取购物车信息
     */
    @GetMapping("/b2c_cart/list")
    public Object b2c_cart() {
        Map data = getCommonShopCartInfoList();
        return new ResultVO(data, CConstant.WEB_HOST + "/h5/pageb2c/b2c_cart/index");
    }



    /**
     * 加购商品
     */
    @GetMapping("/b2c_cart/add")
    public Object b2c_cart(@RequestParam String productid,
                           @RequestParam String amount,
                           @RequestParam String ksid
                           ) {
        UserInfo defaultUser = userInfoService.getDefaultUser();
        shopCartItemService.addItem(defaultUser.getId(),productid, amount);
        Map data = getCommonShopCartInfoList();
        return new ResultVO(data, CConstant.WEB_HOST + "/h5/pageb2c/b2c_cart/index");
    }

    /**
     * 查询购物车列表 通讯方法
     * @return
     */
    private Map getCommonShopCartInfoList() {
        UserInfo defaultUser = userInfoService.getDefaultUser();
        List<ShopCartItemInfo> cartItemInfos = shopCartItemService.getShopCartInfo(defaultUser.getId());
        List<ShopCartItemVO> cartList = cartItemConvert.getShoppingCartItemVOS(cartItemInfos);
        BigDecimal sumMoney = cartItemInfos.stream().map(m -> m.getAmount().multiply(m.getPrice())).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumNum = cartItemInfos.stream().map(ShopCartItemInfo::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        return CCommonUtils.ofMap("cartList", cartList, "total_money", DDecimalUtil.format(sumMoney), "total_num", DDecimalUtil.format(sumNum));
    }
}
