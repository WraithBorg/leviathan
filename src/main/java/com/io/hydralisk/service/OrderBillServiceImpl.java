package com.io.hydralisk.service;

import com.io.hydralisk.domain.*;
import com.io.hydralisk.dto.CreateOrderDTO;
import com.io.hydralisk.eum.DeliveryStatus;
import com.io.hydralisk.eum.OrderState;
import com.io.hydralisk.eum.PayStatus;
import com.io.hydralisk.eum.PayType;
import com.io.hydralisk.mapper.*;
import com.io.hydralisk.service.usb.OrderBillService;
import com.io.hydralisk.util.BillNoUtil;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.util.DDecimalUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderBillServiceImpl implements OrderBillService {
    @Resource
    private OrderLogisticsMapper logisticsMapper;
    @Resource
    private OrderDetailMapper detailMapper;
    @Resource
    private OrderBillMapper billMapper;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private ShopCartItemMapper shopCartItemMapper;
    @Resource
    private ReceiptInfoMapper receiptInfoMapper;

    /**
     * 创建订单
     */
    @Override
    public void createOrder(CreateOrderDTO dto) {

        UserInfo defaultUser = userInfoMapper.getDefaultUser();
        // Prepared
        List<String> cartIds = dto.getCartid();
        List<ShopCartItemInfo> cartItemInfos = cartIds.stream().map(s -> shopCartItemMapper.selectById(s)).collect(Collectors.toList());
        BigDecimal totalAmount = cartItemInfos.stream().map(ShopCartItemInfo::getAmount).reduce(BigDecimal::add).get();
        BigDecimal sumMoney = cartItemInfos.stream().map(m -> m.getAmount().multiply(m.getPrice())).reduce(BigDecimal::add).get();

        String billId = UUID.randomUUID().toString().replace("-", "");
        String logisticsId = UUID.randomUUID().toString().replace("-", "");
        // Install OrderBill And Save
        OrderBill orderBill = new OrderBill();
        orderBill.setId(billId);
        orderBill.setCreateTime(new Date());
        orderBill.setOrderNo(BillNoUtil.createNo());
        orderBill.setLogisticsId(logisticsId);
        orderBill.setRemark(dto.getComment());
        orderBill.setSumMoney(DDecimalUtil.setScale(sumMoney));
        orderBill.setPayType(PayType.getId(dto.getPaytype()));
        orderBill.setPayMoney(DDecimalUtil.setScale(sumMoney));
        orderBill.setItemAmountTotal(DDecimalUtil.setScale(totalAmount));
        orderBill.setFreight(BigDecimal.ZERO);// TODO
        orderBill.setPayStatus(PayStatus.WAIT_PAY.id);
        orderBill.setUserId(defaultUser.getId());
        orderBill.setState(OrderState.UN_PAY.id);
        billMapper.insert(orderBill);
        // Install Detail And Save
        for (ShopCartItemInfo info : cartItemInfos) {
            OrderDetail dt = new OrderDetail();
            dt.setUserId(defaultUser.getId());
            dt.setCreateTime(new Date());
            dt.setOrderId(billId);
            dt.setItemId(info.getItemId());
            dt.setItemName(info.getItemName());
            dt.setAmount(DDecimalUtil.setScale(info.getAmount()));
            dt.setSumMoney(DDecimalUtil.setScale(info.getAmount().multiply(info.getPrice())));
            dt.setItemPrice(info.getPrice());
            dt.setRemark("");//TODO
            dt.setItemImgUrl(info.getItemImgUrl());
            detailMapper.insert(dt);
        }
        //  Install OrderLogistics And Save
        ReceiptInfo receiptInfo = receiptInfoMapper.selectById(dto.getUser_address_id());
        OrderLogistics logistics = new OrderLogistics();
        logistics.setId(logisticsId);
        logistics.setOrderId(billId);
        logistics.setExpressNo(null);
        logistics.setCneeRealName(receiptInfo.getTrueName());
        logistics.setCneeTelephone(receiptInfo.getTelephone());
        logistics.setCneeAddress(receiptInfo.getAddress());
        logistics.setFreight(BigDecimal.ZERO);// TODO
        logistics.setDeliveryTime(null);
        logistics.setStatus(DeliveryStatus.WAIT_DELIVERY.id);
        logistics.setCreateTime(new Date());
        logistics.setUpdateTime(new Date());
        logisticsMapper.insert(logistics);
        // 下单完成 清空购物车
        cartItemInfos.forEach(m -> shopCartItemMapper.deleteById(m.getId()));
    }

    /**
     * 查询订单列表
     */
    @Override
    public List<OrderBill> getMyOrder(String userId, String type) {
        Integer state = OrderState.getId(type);
        if (state == null) {
            return billMapper.selectByMap(CCommonUtils.ofMap(OrderBill.t.user_id, userId));
        }
        List<OrderBill> orderBills = billMapper.selectByMap(CCommonUtils.ofMap(OrderBill.t.user_id, userId, OrderBill.t.state, state));
        return orderBills;
    }
}
