package com.io.hydralisk.convert;

import com.io.hydralisk.domain.OrderBill;
import com.io.hydralisk.domain.OrderDetail;
import com.io.hydralisk.domain.OrderLogistics;
import com.io.hydralisk.mapper.OrderBillMapper;
import com.io.hydralisk.mapper.OrderDetailMapper;
import com.io.hydralisk.mapper.OrderLogisticsMapper;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.vo.OrderBillAddrVO;
import com.io.hydralisk.vo.OrderBillVO;
import com.io.hydralisk.vo.OrderDetailVO;
import com.io.hydralisk.vo.ShowOrderGroupVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ShowOrderGroupConvert {
    @Resource
    private OrderBillAddrConvert orderBillAddrConvert;
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Resource
    private OrderDetailConvert orderDetailConvert;
    @Resource
    private OrderLogisticsMapper logisticsMapper;
    @Resource
    private OrderBillConvert orderBillConvert;
    @Resource
    private OrderBillMapper orderBillMapper;

    /**
     * 查看订单详情
     */
    public ShowOrderGroupVO getShowOrderGroup(String orderId) {
        //
        OrderLogistics logistics = logisticsMapper.getByOrderId(orderId);
        OrderBillAddrVO orderBillAddrVO = orderBillAddrConvert.getOrderBillAddrVO(logistics);
        //
        List<OrderDetail> orderDetails = orderDetailMapper.selectByMap(CCommonUtils.ofMap(OrderDetail.t.order_id, orderId));
        List<OrderDetailVO> orderDetailVOS = orderDetailConvert.getOrderDetailVOS(orderDetails);
        //
        OrderBill orderBill = orderBillMapper.selectById(orderId);
        OrderBillVO orderBillVO = orderBillConvert.getOrderBill4ListVO(orderBill);
        //
        ShowOrderGroupVO vo = new ShowOrderGroupVO();
        vo.setAddr(orderBillAddrVO);
        vo.setOrder(orderBillVO);
        vo.setProlist(orderDetailVOS);
        return vo;
    }
}
