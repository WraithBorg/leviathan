package com.io.hydralisk.controller;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.service.usb.UserInfoService;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

/**
 * 消息通知
 */
@RequestMapping("/notice")
@RestController
public class NoticeInfoController {
    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/mynotice")
    public Object mynotice() {
        Map data = CCommonUtils.ofMap("data", new ArrayList<>(), "pagelist", false, "rscount", 0);
        return new ResultVO(data, CConstant.WEB_HOST + "/h5/pages/notice/my");
    }
}
