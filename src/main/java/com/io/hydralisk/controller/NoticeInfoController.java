package com.io.hydralisk.controller;

import com.io.hydralisk.constant.PageConst;
import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.util.CCommonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

/**
 * 消息通知
 */
@RestController
public class NoticeInfoController {
    /**
     * 我的消息
     */
    @GetMapping("/notice/mynotice")
    public MsgResult mynotice() {
        Map data = CCommonUtils.ofMap("data", new ArrayList<>(), "pagelist", false, "rscount", 0);
        return MsgResult.doneUrl(data, PageConst.MY_NOTICE);
    }
}
