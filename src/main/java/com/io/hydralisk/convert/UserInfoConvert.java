package com.io.hydralisk.convert;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.util.CommonUtils;
import com.io.hydralisk.vo.UserInfoVO;
import com.io.hydralisk.vo.UserPassVO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserInfoConvert {


    public UserInfoVO getUserVO(UserInfo userInfo) {
        UserInfoVO vo = new UserInfoVO();
        vo.setBirthday(CommonUtils.formatDate(new Date()));
        vo.setCreatetime(CommonUtils.formatDate(new Date()));
        vo.setFollow_num(0);
        vo.setFollowed_num(0);
        vo.setGender(0);
        vo.setGold(0);
        vo.setGrade(0);
        vo.setInvite_userid(0L);
        vo.setIs_auth(0);
        vo.setLasttime(CommonUtils.formatDate(new Date()));
        vo.setMoney("0.00");
        vo.setNickname(userInfo.getNickName());
        vo.setStatus(10);
        vo.setTelephone(CConstant.DEFAULT_TELEPHONE);
        vo.setUser_head(CConstant.DEFAULT_HEAD_IMG);
        vo.setUser_type(1);
        vo.setUserid(userInfo.getId());
        vo.setUsername(userInfo.getNickName());
        return vo;
    }

    public UserPassVO getUserPassVO(UserInfo userInfo) {
        UserPassVO vo = new UserPassVO();
        vo.setGender(0);
        vo.setGold(0);
        vo.setGender(0);
        vo.setMoney("0");
        vo.setNickname(userInfo.getNickName());
        vo.setUser_head(CConstant.DEFAULT_HEAD_IMG);
        vo.setUserid(userInfo.getId());
        vo.setUsername(userInfo.getNickName());
        return vo;
    }
}
