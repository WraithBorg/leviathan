package com.io.hydralisk.convert;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.constant.PageConst;
import com.io.hydralisk.domain.OrderDetail;
import com.io.hydralisk.util.DDateUtil;
import com.io.hydralisk.util.DDecimalUtil;
import com.io.hydralisk.vo.OrderDetailVO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDetailConvert {

    OrderDetailVO getOrderDetailVO(OrderDetail info) {

        OrderDetailVO vo = new OrderDetailVO();
        vo.setAmount(info.getAmount());
        vo.setCreatetime(DDateUtil.format(info.getCreateTime()));
        vo.setId(info.getId());
        vo.setImgurl( PageConst.IMG_PATH + "index_flash_01.png");
        vo.setPrice(DDecimalUtil.format(info.getItemPrice()));
        vo.setProductid(info.getItemId());
        vo.setTitle(info.getItemName());
        vo.setTotal_num(new BigDecimal(99999));//库存数量
        vo.setUserid(info.getUserId());
        //
        vo.setWeight(null);
        vo.setPt_price("0");
        vo.setStime(0);
        vo.setOtype("");
        vo.setKs_title("");
        vo.setEtime(0);
        return vo;
    }

    List<OrderDetailVO> getOrderDetailVOS(List<OrderDetail> infos) {
        List<OrderDetailVO> collect = infos.stream().map(this::getOrderDetailVO).collect(Collectors.toList());
        return collect;
    }
}
