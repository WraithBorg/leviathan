package com.io.hydralisk.service.usb;

import com.io.hydralisk.domain.UserInfo;

public interface UserInfoService {

//    UserInfo getDefaultUser();
    UserInfo getUserByTelephone(String telePhone);
}
