package com.io.hydralisk.convert;

import com.io.hydralisk.domain.OrderBill;
import com.io.hydralisk.domain.OrderDetail;
import com.io.hydralisk.domain.OrderLogistics;
import com.io.hydralisk.eum.DeliveryStatus;
import com.io.hydralisk.mapper.OrderDetailMapper;
import com.io.hydralisk.mapper.OrderLogisticsMapper;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.vo.OrderBill4ListVO;
import com.io.hydralisk.vo.OrderBillAddrVO;
import com.io.hydralisk.vo.OrderDetailVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderBill4ListConvert {
    @Resource
    private OrderBillAddrConvert orderBillAddrConvert;
    @Resource
    private OrderLogisticsMapper logisticsMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Resource
    private OrderDetailConvert orderDetailConvert;
    @Resource
    private OrderBillConvert orderBillConvert;

    /**
     * vo
     */
    public OrderBill4ListVO getOrderBill4ListVO(OrderBill info) {
        OrderBill4ListVO vo = orderBillConvert.getOrderBill4ListVO(info);
        // 收货地址
        OrderLogistics logistics = logisticsMapper.getByOrderId(info.getId());
        if (logistics != null) {
            vo.setExpress_no(logistics.getExpressNo());
            vo.setIsreceived(DeliveryStatus.isReceived(logistics.getStatus()));
            //
            OrderBillAddrVO orderBillAddrVO = orderBillAddrConvert.getOrderBillAddrVO(logistics);
            vo.setAddr(orderBillAddrVO);
        }
        // 商品明细
        List<OrderDetail> orderDetails = orderDetailMapper.selectByMap(CCommonUtils.ofMap(OrderDetail.t.order_id, info.getId()));
        List<OrderDetailVO> orderDetailVOS = orderDetailConvert.getOrderDetailVOS(orderDetails);
        vo.setProlist(orderDetailVOS);
        return vo;
    }

    public List<OrderBill4ListVO> getOrderBill4ListVOS(List<OrderBill> infos) {
        List<OrderBill4ListVO> collect = infos.stream().map(this::getOrderBill4ListVO).collect(Collectors.toList());
        return collect;
    }

}
