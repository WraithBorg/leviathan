package com.io.hydralisk.controller;

import com.io.hydralisk.constant.PageConst;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.service.usb.UserFavItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 收藏商品
 */
@RestController
public class ItemFavController {
    @Resource
    private UserFavItemService userFavInfoService;
    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 收藏商品
     */
    @RequestMapping("/item/itemfav")
    public MsgResult toggle(String objectid) {
        UserInfo defaultUser = userInfoMapper.getDefaultUser();
        String method = userFavInfoService.favItem(defaultUser.getId(), objectid);
        return MsgResult.doneUrl(method, PageConst.PRODUCT_SHOW + objectid);
    }


}
