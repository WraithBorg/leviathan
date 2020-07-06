package com.io.hydralisk.controller;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.util.CCommonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RequestMapping("/html")
@RestController
public class HtmlController {

    @RequestMapping("/aboutus")
    public Object aboutus() {
        Map data = CCommonUtils.ofMapN("content", "<p>带你去爬山</p>",
                "dateline", new Date(), "equation", "",
                "id", 1,
                "info", "",
                "status", 2,
                "title", "关于我们",
                "type_id", 1, "word", "关于我们");
        Map dataMap = CCommonUtils.ofMap("data", data);
        Map ofMap = CCommonUtils.ofMap("error", 0, "message", "success", "data", dataMap, "url", CConstant.WEB_HOST+"/h5/pages/html/aboutus");
        return ofMap;
    }
}
