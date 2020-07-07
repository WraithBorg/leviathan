package com.io.hydralisk.service.usb;

import com.io.hydralisk.domain.OrderBill;
import com.io.hydralisk.dto.CreateOrderDTO;

import java.util.List;

public interface OrderBillService {
    void createOrder(CreateOrderDTO createOrderDTO);

    List<OrderBill> getMyOrder(String userId,String type);
}
