package com.io.hydralisk.controller;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.convert.OrderBill4ListConvert;
import com.io.hydralisk.convert.ReceiptInfoConvert;
import com.io.hydralisk.convert.ShoppingCartItemConvert;
import com.io.hydralisk.convert.ShowOrderGroupConvert;
import com.io.hydralisk.domain.OrderBill;
import com.io.hydralisk.domain.ReceiptInfo;
import com.io.hydralisk.domain.ShopCartItemInfo;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.dto.CreateOrderDTO;
import com.io.hydralisk.mapper.OrderBillMapper;
import com.io.hydralisk.service.usb.OrderBillService;
import com.io.hydralisk.service.usb.ReceiptInfoService;
import com.io.hydralisk.service.usb.ShopCartItemService;
import com.io.hydralisk.service.usb.UserInfoService;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.vo.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/b2c_order")
@RestController
public class OrderBillController {
    @Resource
    private ReceiptInfoConvert receiptConvert;
    @Resource
    private ReceiptInfoService receiptInfoService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private ShopCartItemService shopCartItemService;
    @Resource
    private ShoppingCartItemConvert cartItemConvert;
    @Resource
    private OrderBillService orderBillService;
    @Resource
    private OrderBillMapper orderBillMapper;
    @Resource
    private OrderBill4ListConvert orderBill4ListConvert;
    @Resource
    private ShowOrderGroupConvert showOrderGroupConvert;
    /**
     * 确认订单 显示商品和收货地址界面
     */
    @GetMapping("/confirm")
    public Object confirm(@RequestParam(required = false) String user_address_id) {
        // 获取登陆人信息
        UserInfo defaultUser = userInfoService.getDefaultUser();
        // 查询收货地址列表
        List<ReceiptInfo> receiptInfos = receiptInfoService.selectList(defaultUser.getId());
        List<ReceiptInfoVO> receiptVOS = receiptConvert.getReceiptVOS(receiptInfos);
        // 查询购物车列表
        List<ShopCartItemInfo> cartItemInfos = shopCartItemService.getShopCartInfo(defaultUser.getId());
        List<ShopCartItemVO> cartList = cartItemConvert.getShoppingCartItemVOS(cartItemInfos);
        // 查询支付方式列表
        Map<String, String> payType = CConstant.PAY_TYPE;
        // 计算金额 数量
        BigDecimal totalMoney = cartItemInfos.stream().map(m -> m.getAmount().multiply(m.getPrice())).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalNum = cartItemInfos.stream().map(ShopCartItemInfo::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal goodsMoney = totalMoney;
        // 设置默认值
        String defaultAddressId = user_address_id;// 默认收货地址
        if (CCommonUtils.isBlank(defaultAddressId) && receiptVOS.size() > 0) {
            defaultAddressId = receiptVOS.get(0).getId();
        }
        String defaultPayType = null; // 默认支付方式
        if (payType.keySet().size() > 0) {
            String key = payType.keySet().stream().findFirst().get();
            defaultPayType = payType.get(key);
        }

        //
        ConfirmDetailVO confirmDetailVO = new ConfirmDetailVO();
        confirmDetailVO.setAddrList(receiptVOS);
        confirmDetailVO.setPaytypeList(payType);
        confirmDetailVO.setCartList(cartList);
        confirmDetailVO.setTotal_money(totalMoney);
        confirmDetailVO.setTotal_num(totalNum);
        confirmDetailVO.setGoods_money(goodsMoney);
        confirmDetailVO.setPaytype(defaultPayType);
        confirmDetailVO.setUser_address_id(defaultAddressId);
        return new ResultVO(confirmDetailVO, CConstant.WEB_HOST + "/h5/pageb2c/b2c_order/confirm");
    }

    /**
     * 查询我的订单
     */
    @GetMapping("/myorder")
    public Object myorder(String type) {

        UserInfo defaultUser = userInfoService.getDefaultUser();
        List<OrderBill> myOrder = orderBillService.getMyOrder(defaultUser.getId(),type);

        List<OrderBill4ListVO> orderBillVOS = orderBill4ListConvert.getOrderBill4ListVOS(myOrder);
        Map<String, Object> data = CCommonUtils.ofMap("type", type, "per_page", 0, "list", orderBillVOS);
        return new ResultVO(data, CConstant.WEB_HOST + "/h5/pageb2c/b2c_order/my");
    }

    /**
     * 创建订单
     */
    @PostMapping("/createorder")
    public Object createOrder(CreateOrderDTO createOrderDTO) {
        System.out.println(createOrderDTO.getBackurl());
        orderBillService.createOrder(createOrderDTO);
        return null;
    }

    /**
     * 查看订单详情
     */
    @GetMapping("/showdetails")
    public Object showDetail(String orderid){
        ShowOrderGroupVO showOrderGroup = showOrderGroupConvert.getShowOrderGroup(orderid);
        return new ResultVO(showOrderGroup, CConstant.WEB_HOST + "/h5/pageb2c/b2c_order/show?orderid="+orderid);
    }
    /**
     * 取消订单
     */
    @GetMapping("/cancelOrder")
    public Object cancelOrder(String orderid){
        orderBillService.cancelOrder(orderid);
        return new ResultVO(new ArrayList<>(), CConstant.WEB_HOST + "/h5/pageb2c/b2c_order/show?orderid="+orderid);
    }
}
