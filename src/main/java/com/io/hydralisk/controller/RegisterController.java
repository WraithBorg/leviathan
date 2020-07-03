package com.io.hydralisk.controller;

import com.io.hydralisk.dto.RegisterDTO;
import com.io.hydralisk.vo.RegisterVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    public RegisterVO regsave(RegisterDTO registerDTO){
        logger.info("用戶註冊註冊註冊。注册");
        return new RegisterVO(1, "手机验证码出错", new ArrayList<>(), "http://localhost:8080/h5/pages/register/index");
    }
}
