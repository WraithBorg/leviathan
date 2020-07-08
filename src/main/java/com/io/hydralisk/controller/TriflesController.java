package com.io.hydralisk.controller;

import com.io.hydralisk.constant.PageConst;
import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.vo.AboutUsVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * insignificance trifles
 */
@RequestMapping("/trifles")
@RestController
public class TriflesController {
    @Resource
    private AboutUsVO aboutUsVO;

    @RequestMapping("/aboutus")
    public MsgResult aboutus() {
        return MsgResult.doneUrl(aboutUsVO, PageConst.ABOUT_US);
    }
}
