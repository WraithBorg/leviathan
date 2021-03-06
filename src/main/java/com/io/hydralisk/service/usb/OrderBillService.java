package com.io.hydralisk.service.usb;

import com.io.hydralisk.domain.OrderBill;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.dto.CreateOrderDTO;

import java.util.List;

public interface OrderBillService {
    /**
     * 创建订单
     */
    void createOrder(UserInfo currentUser,CreateOrderDTO createOrderDTO);
    /**
     * 查询订单列表
     */
    List<OrderBill> getMyOrder(String userId,String type);
    /**
     * 取消订单
     */
    void cancelOrder(String id);
    /**
     * 清除已取消订单
     */
    void clearOrder(String orderid);
}
