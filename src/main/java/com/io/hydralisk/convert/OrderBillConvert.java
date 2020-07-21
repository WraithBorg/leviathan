package com.io.hydralisk.convert;

import com.io.hydralisk.domain.OrderBill;
import com.io.hydralisk.eum.OrderState;
import com.io.hydralisk.eum.PayType;
import com.io.hydralisk.util.DDateUtil;
import com.io.hydralisk.util.DDecimalUtil;
import com.io.hydralisk.vo.OrderBill4ListVO;
import org.springframework.stereotype.Component;

@Component
public class OrderBillConvert {
    public OrderBill4ListVO getOrderBill4ListVO(OrderBill info) {

        OrderBill4ListVO vo = new OrderBill4ListVO();
        vo.setComment(info.getRemark());
        vo.setCreatetime(DDateUtil.format(info.getCreateTime()));
        vo.setGoods_money(DDecimalUtil.format(info.getSumMoney()));
        vo.setIspay(info.getState().equals(OrderState.UN_SEND.id) ? 1 : 0);
        vo.setMoney(DDecimalUtil.format(info.getSumMoney()));
        vo.setOrderid(info.getId());
        vo.setOrderno(info.getOrderNo());
        vo.setPaytype(PayType.getTtype(info.getPayType()));
        vo.setPaymoney(DDecimalUtil.format(info.getPayMoney()));
        vo.setTotal_num(info.getItemAmountTotal());
        vo.setUser_address_id("TODO ADDRESS_ID");
        vo.setUserid(info.getUserId());

        vo.setExpress_money(DDecimalUtil.format(info.getFreight()));
        vo.setStatus_name(OrderState.getText(info.getState()));
        vo.setStatus(info.getState());
        return vo;
    }
}
