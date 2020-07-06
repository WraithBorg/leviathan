package com.io.hydralisk.controller;

import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.dto.RegisterDTO;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户注册
 */
@RequestMapping("/register")
@RestController
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 获取验证码
     */
    @CrossOrigin(origins = "*")
    @GetMapping(params = "m=SendSms")
    public void SendSms(@RequestParam(required = false) String telephone) {
        // 发送验证码 TODO
        logger.info("获取验证码。");
    }

    @PostMapping(params = "m=regsave")
    public Object regsave(RegisterDTO registerDTO) {
        logger.info("用戶註冊註冊註冊。注册");
        // validate
        if (CommonUtils.isBlank(registerDTO.getNickname())){
            return CommonUtils.ofMap("error", 1, "message", "请填写昵称");
        }if (CommonUtils.isBlank(registerDTO.getTelephone())){
            return CommonUtils.ofMap("error", 1, "message", "请填写手机号");
        }if (CommonUtils.isBlank(registerDTO.getPassword())){
            return CommonUtils.ofMap("error", 1, "message", "请填写密码");
        }

        List<UserInfo> v_telePhone = userInfoMapper.selectByMap(CommonUtils.ofMap("telephone", registerDTO.getTelephone()));
        if (v_telePhone.size() > 0){
            return CommonUtils.ofMap("error", 1, "message", "该手机号已注册");
        }
        List<UserInfo> v_nickName = userInfoMapper.selectByMap(CommonUtils.ofMap("nickname", registerDTO.getNickname()));
        if (v_nickName.size() > 0){
            return CommonUtils.ofMap("error", 1, "message", "该昵称已注册");
        }

        // install
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(registerDTO.getNickname());
        userInfo.setTelePhone(registerDTO.getTelephone());
        userInfo.setPassword(registerDTO.getPassword());
        // save
        int insert = userInfoMapper.insert(userInfo);
        if (insert > 0){
            Map data = CommonUtils.ofMap("authcode", "zxcvb21345");
            return CommonUtils.ofMap("error", 0, "data", data);
        }else {
            return CommonUtils.ofMap("error", 1, "message", "注册失败");
        }
    }
}
