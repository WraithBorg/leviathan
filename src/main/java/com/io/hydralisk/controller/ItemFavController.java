package com.io.hydralisk.controller;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.service.usb.UserFavItemService;
import com.io.hydralisk.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 收藏商品
 */
@RestController
@RequestMapping("/itemfav")
public class ItemFavController {
    @Resource
    private UserFavItemService userFavInfoService;
    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 收藏商品
     */
    @GetMapping("/toggle")
    public Object toggle(String objectid) {
        UserInfo defaultUser = userInfoMapper.getDefaultUser();
        String method = userFavInfoService.favItem(defaultUser.getId(), objectid);
        return new ResultVO(method, CConstant.WEB_HOST + "/h5/pageb2c/b2c_product/show?id=" + objectid);
    }


}
