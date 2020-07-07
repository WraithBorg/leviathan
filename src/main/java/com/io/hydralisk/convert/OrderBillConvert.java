package com.io.hydralisk.convert;

import com.io.hydralisk.domain.OrderBill;
import com.io.hydralisk.domain.OrderDetail;
import com.io.hydralisk.domain.OrderLogistics;
import com.io.hydralisk.eum.DeliveryStatus;
import com.io.hydralisk.eum.PayStatus;
import com.io.hydralisk.eum.PayType;
import com.io.hydralisk.mapper.OrderDetailMapper;
import com.io.hydralisk.mapper.OrderLogisticsMapper;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.util.DDateUtil;
import com.io.hydralisk.util.DDecimalUtil;
import com.io.hydralisk.vo.OrderBillAddrVO;
import com.io.hydralisk.vo.OrderBillVO;
import com.io.hydralisk.vo.OrderDetailVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderBillConvert {
    @Resource
    private OrderBillAddrConvert orderBillAddrConvert;
    @Resource
    private OrderLogisticsMapper logisticsMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Resource
    private OrderDetailConvert orderDetailConvert;
    /**
     * vo
     */
    public OrderBillVO getOrderBillVO(OrderBill info) {

        OrderBillVO vo = new OrderBillVO();
        vo.setComment(info.getRemark());
        vo.setComment(DDateUtil.format(info.getCreateTime()));
        vo.setGoods_money(DDecimalUtil.format(info.getSumMoney()));
        vo.setIspay(info.getPayStatus());
        vo.setMoney(DDecimalUtil.format(info.getSumMoney()));
        vo.setOrderid(info.getId());
        vo.setOrderno(info.getOrderNo());
        vo.setPaytype(PayType.getTtype(info.getPayStatus()));
        vo.setPaymoney(DDecimalUtil.format(info.getPayMoney()));
        vo.setTotal_num(info.getItemAmountTotal());
        vo.setUser_address_id("TODO ADDRESS_ID");
        vo.setUserid(info.getUserId());

        vo.setExpress_money(DDecimalUtil.format(info.getFreight()));
        vo.setStatus_name(PayStatus.getText(info.getPayStatus()));
        vo.setStatus(info.getPayStatus());
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
    public List<OrderBillVO> getOrderBillVOS(List<OrderBill> infos){
        List<OrderBillVO> collect = infos.stream().map(this::getOrderBillVO).collect(Collectors.toList());
        return collect;
    }

}
