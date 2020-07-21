package com.io.hydralisk.controller;

import com.io.hydralisk.constant.PageConst;
import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.util.CCommonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

/**
 * 消费记录
 */
@RestController
public class PayLogController {
    @Resource
    private HttpServletRequest httpServletRequest;
    @GetMapping("/paylog/mypaylog")
    public MsgResult mypaylog() {
        Map data = CCommonUtils.ofMap("data", new ArrayList<>(), "pagelist", false, "rscount", 0, "per_page", 0);
        return MsgResult.doneUrl(data, PageConst.MY_PAY_LOG);
    }
}
