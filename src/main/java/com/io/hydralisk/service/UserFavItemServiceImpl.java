package com.io.hydralisk.service;

import com.io.hydralisk.domain.UserFavItemInfo;
import com.io.hydralisk.mapper.UserFavItemMapper;
import com.io.hydralisk.service.usb.UserFavItemService;
import com.io.hydralisk.util.CCommonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserFavItemServiceImpl implements UserFavItemService {

    @Resource
    private UserFavItemMapper userFavItemMapper;

    @Override
    public Integer hasFav(String userId, String itemId) {
        List<UserFavItemInfo> userFavItemInfos = userFavItemMapper.selectByMap(CCommonUtils.ofMap(UserFavItemInfo.t.user_id, userId, UserFavItemInfo.t.item_id, itemId));
        if (userFavItemInfos.size() > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public String favItem(String userId, String itemId) {
        List<UserFavItemInfo> userFavItemInfos = userFavItemMapper.selectByMap(CCommonUtils.ofMap(UserFavItemInfo.t.user_id, userId, UserFavItemInfo.t.item_id, itemId));
        if (userFavItemInfos.size() == 0) {
            UserFavItemInfo info = new UserFavItemInfo();
            info.setUserId(userId);
            info.setItemId(itemId);
            userFavItemMapper.insert(info);
            return "add";
        }
        userFavItemInfos.forEach(m -> userFavItemMapper.deleteById(m.getId()));
        return "delete";
    }
}
