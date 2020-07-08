package com.io.hydralisk.controller;

import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.chip.SprContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class IndexController {

    @Resource
    private MessageSource messageSource;
    @RequestMapping("/")
    public MsgResult index(){
        return MsgResult.done(CCommonUtils.ofMap("姓名","user.name"), "user.name");
    }
    @RequestMapping("/test")
    public MsgResult star(){
        return MsgResult.done(CCommonUtils.ofMap("姓名","xx"), "成功");

    }
}
