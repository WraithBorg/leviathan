package com.io.hydralisk.controller;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

/**
 * 充值记录
 */
@RequestMapping("/recharge")
@RestController
public class RechargeController {

    @GetMapping("/myrecharge")
    public Object myRecharge(){

        Map data = CCommonUtils.ofMap("data", new ArrayList<>(), "pagelist", false, "rscount", 0, "per_page", 0);
        return new ResultVO(data, CConstant.WEB_HOST + "/h5/pages/recharge/my");
    }
}
