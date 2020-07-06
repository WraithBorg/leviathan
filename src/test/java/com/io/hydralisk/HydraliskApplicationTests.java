package com.io.hydralisk;

import com.alibaba.fastjson.JSON;
import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.dto.NavDTO;
import com.io.hydralisk.dto.NavUserDTO;
import com.io.hydralisk.util.CCommonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class HydraliskApplicationTests {

    @Test
    void contextLoads() {
		Map data = CCommonUtils.ofMap("data", new NavUserDTO(), "navList", NavDTO.getNavList());
        Map ofMap = CCommonUtils.ofMap("error", 0, "message", "success", "data", data, "url", CConstant.WEB_HOST+"/h5/pageb2c/b2c_user/index");
        Object o = JSON.toJSON(ofMap);
        System.out.println(o);
    }

}
