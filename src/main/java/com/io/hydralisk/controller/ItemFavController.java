package com.io.hydralisk.controller;

import com.io.hydralisk.constant.PageConst;
import com.io.hydralisk.constant.SessionConst;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.service.usb.UserFavItemService;
import com.io.hydralisk.util.SessionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 收藏商品
 */
@RestController
public class ItemFavController {
    @Resource
    private HttpServletRequest httpServletRequest;
    @Resource
    private UserFavItemService userFavInfoService;
    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 收藏商品
     */
    @RequestMapping("/item/itemfav")
    public MsgResult toggle(String objectid) {
        UserInfo currentUser = SessionUtil.getCurrentUser(httpServletRequest);
        String method = userFavInfoService.favItem(currentUser.getId(), objectid);
        return MsgResult.doneUrl(method, PageConst.PRODUCT_SHOW + objectid);
    }


}
