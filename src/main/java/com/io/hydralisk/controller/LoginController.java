package com.io.hydralisk.controller;

import com.io.hydralisk.constant.PageConst;
import com.io.hydralisk.convert.UserInfoConvert;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.dto.LoginDTO;
import com.io.hydralisk.vo.UserMenuVO;
import com.io.hydralisk.dto.RegisterDTO;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.service.usb.UserInfoService;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.vo.UserInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户登录
 */
@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserInfoConvert userInfoConvert;
    /**
     * 用户登录
     */
    @PostMapping("/login/loginsave")
    public MsgResult loginsave(LoginDTO loginDTO) {
        // validate
        String telephone = loginDTO.getTelephone();
        List<UserInfo> v_telephone = userInfoMapper.selectByMap(CCommonUtils.ofMap(UserInfo.t.telephone, telephone));
        if (v_telephone.size() == 0) {
            return MsgResult.fail("账号不存在");
        }
        UserInfo userInfo = v_telephone.get(0);
        if (!userInfo.getPassword().equals(loginDTO.getPassword())) {
            return MsgResult.fail("账号不存在");
        }
        //
        Map<String, String> data = CCommonUtils.ofMap("authcode", "12JTddcCbJ6T2Iby6d3SeUcy5M8i5U5z2Q1TdEzNiUyQyUyMnAlMjIlM0ElMjI0ODE2ZWIlMjIlMkMlMjJhJTIyJTNBJTIyNWViYiUyMiUyQyUyMmUlMjIlM0ExNTk0MDQzMDcyJTdE", "authcodeLong", "12JTddcCbJ6T2Iby6d3SeUcy5M8i5U5z2Q1TdEzNiUyQyUyMnAlMjIlM0ElMjI0ODE2ZWIlMjIlMkMlMjJhJTIyJTNBJTIyNWViYiUyMiUyQyUyMmUlMjIlM0ExNTk0MDQzMDcyJTJDJTIyZWwlMjIlM0ExNjE5NzkwMjcyJTdE", "backurl", "\\/index.php");
        return MsgResult.doneUrl(data, PageConst.INDEX_PAGE);

    }

    /**
     * 修改密码 获取短信验证码
     */
    @GetMapping("login/sendsms")
    public MsgResult sendsms(String telephone) {
        return MsgResult.doneMsg("获取短信验证码成功");
    }

    /**
     * 修改密码
     */
    @PostMapping("login/findpwdSave")
    public MsgResult findpwdSave(RegisterDTO registerDTO) {
        return MsgResult.doneUrl("密码修改成功，马上登录", PageConst.INDEX_LOGIN);
    }


    /**
     * 登录后获取用户信息
     */
    @RequestMapping("logined/b2c_user")
    public MsgResult b2c_user() {
        UserInfo defaultUser = userInfoService.getDefaultUser();
        UserInfoVO userInfoVO = userInfoConvert.getUserVO(defaultUser);
        Map data = CCommonUtils.ofMap("data", userInfoVO, "navList", UserMenuVO.getNavList());
        return MsgResult.doneUrl(data,PageConst.USER_INDEX);
    }
}
