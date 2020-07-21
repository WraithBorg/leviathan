package com.io.hydralisk.controller;

import com.io.hydralisk.annotate.WithoutLogin;
import com.io.hydralisk.constant.PageConst;
import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.vo.AboutUsVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 关于我们
 * insignificance trifles
 */
@RestController
public class TriflesController {
    @Resource
    private HttpServletRequest httpServletRequest;
    @Resource
    private AboutUsVO aboutUsVO;
    @WithoutLogin
    @RequestMapping("/trifles/aboutus")
    public MsgResult aboutus() {
        return MsgResult.doneUrl(aboutUsVO, PageConst.ABOUT_US);
    }
}
