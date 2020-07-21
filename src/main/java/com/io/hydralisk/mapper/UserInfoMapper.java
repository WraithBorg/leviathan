package com.io.hydralisk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.domain.UserInfo;
import org.apache.ibatis.annotations.Select;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

//    @Select(" select * from user_info where telephone = '" + CConstant.DEFAULT_TELEPHONE + "' ")
//    public UserInfo getDefaultUser();

}
