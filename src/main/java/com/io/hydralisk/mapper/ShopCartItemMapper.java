package com.io.hydralisk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.io.hydralisk.domain.ShopCartItemInfo;
import org.apache.ibatis.annotations.Select;

public interface ShopCartItemMapper  extends BaseMapper<ShopCartItemInfo> {

    @Select(" select * from shop_cart_item where item_id = #{itemId} and user_id = #{userId}")
    ShopCartItemInfo getByUserItem(String itemId, String userId);


}
