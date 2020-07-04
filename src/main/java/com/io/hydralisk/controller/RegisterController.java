package com.io.hydralisk.controller;

import com.io.hydralisk.dto.RegisterDTO;
import com.io.hydralisk.util.CommonUtils;
import com.io.hydralisk.vo.RegisterVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

@RequestMapping("/register")
@RestController
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @CrossOrigin(origins = "*")
    @GetMapping(params = "m=SendSms")
    public void SendSms(@RequestParam(required = false) String telephone) {
        logger.info("获取验证码。");
    }

    @PostMapping(params = "m=regsave")
    public Object regsave(RegisterDTO registerDTO) {
        logger.info("用戶註冊註冊註冊。注册");
//        if (CommonUtils.isBlank(registerDTO.getTelephone()) || !CommonUtils.confPhone(registerDTO.getTelephone())) {
//            return CommonUtils.ofMap("error", 1, "message", "请输入正确的手机号格式","data",new String[]{},"url","http://localhost:8080/h5/pages/register/index");
//        }
        Map  data = CommonUtils.ofMap(  "authcode", "zxcvb21345");
        return CommonUtils.ofMap("error", 0, "data",data);
    }
}
