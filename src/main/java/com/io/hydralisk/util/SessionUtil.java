package com.io.hydralisk.util;

import com.io.hydralisk.constant.SessionConst;
import com.io.hydralisk.domain.UserInfo;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
    /**
     * 获取当前登陆人
     */
    public static UserInfo getCurrentUser(HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(SessionConst.CURRENT_USER);
        if (attribute == null){
            return null;
        }
        UserInfo currentUser = (UserInfo) attribute;
        return currentUser;
    }
}
