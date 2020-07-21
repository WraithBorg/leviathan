package com.io.hydralisk.controller;

import com.io.hydralisk.annotate.WithoutLogin;
import com.io.hydralisk.constant.PageConst;
import com.io.hydralisk.convert.ShoppingCartItemConvert;
import com.io.hydralisk.domain.ShopCartItemInfo;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.service.usb.ShopCartItemService;
import com.io.hydralisk.service.usb.UserInfoService;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.util.DDecimalUtil;
import com.io.hydralisk.util.SessionUtil;
import com.io.hydralisk.vo.ShopCartItemVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 购物车
 */
@RestController
public class ShopCartController {
    @Resource
    private HttpServletRequest httpServletRequest;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private ShopCartItemService shopCartItemService;
    @Resource
    private ShoppingCartItemConvert cartItemConvert;

    /**
     * 获取购物车信息
     */
//    @WithoutLogin
    @GetMapping("/shoppingcart/b2c_cart/list")
    public MsgResult b2c_cart() {
       /* UserInfo defaultUser = SessionUtil.getCurrentUser(httpServletRequest);
        if (defaultUser == null){
            Map  dataMap = CCommonUtils.ofMap("cartList", new ArrayList<>(),
                    "total_money", 0,
                    "total_num", 0);
            return MsgResult.doneUrl(dataMap, PageConst.CART_SHOW);
        }*/
        Map data = getCommonShopCartInfoList();
        return MsgResult.doneUrl(data, PageConst.CART_SHOW);
    }

    /**
     * 加购商品
     */
    @GetMapping("/shoppingcart/b2c_cart/add")
    public MsgResult b2c_cart(@RequestParam String productid,
                              @RequestParam String amount,
                              @RequestParam String ksid
    ) {
        UserInfo defaultUser = SessionUtil.getCurrentUser(httpServletRequest);
        shopCartItemService.addItem(defaultUser.getId(), productid, amount);
        Map data = getCommonShopCartInfoList();
        return MsgResult.doneUrl(data, PageConst.CART_SHOW);
    }

    /**
     * 查询购物车列表 通用方法
     */
    private Map getCommonShopCartInfoList() {
        UserInfo defaultUser = SessionUtil.getCurrentUser(httpServletRequest);
        List<ShopCartItemInfo> cartItemInfos = shopCartItemService.getShopCartInfo(defaultUser.getId());
        List<ShopCartItemVO> cartList = cartItemConvert.getShoppingCartItemVOS(cartItemInfos);
        BigDecimal sumMoney = cartItemInfos.stream().map(m -> m.getAmount().multiply(m.getPrice())).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumNum = cartItemInfos.stream().map(ShopCartItemInfo::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        return CCommonUtils.ofMap("cartList", cartList, "total_money", DDecimalUtil.format(sumMoney), "total_num", DDecimalUtil.format(sumNum));
    }
}
