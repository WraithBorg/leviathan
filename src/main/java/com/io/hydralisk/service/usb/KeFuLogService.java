package com.io.hydralisk.service.usb;

import com.io.hydralisk.domain.KeFuLogInfo;
import com.io.hydralisk.domain.UserInfo;

import java.util.List;

public interface KeFuLogService {
    /**
     * 获取用户客服记录列表
     */
    List<KeFuLogInfo> getMyLog(UserInfo currentUser);

}
