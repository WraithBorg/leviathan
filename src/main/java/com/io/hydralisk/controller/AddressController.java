package com.io.hydralisk.controller;

import com.io.hydralisk.domain.ReceiptInfo;
import com.io.hydralisk.dto.AddressDTO;
import com.io.hydralisk.mapper.ReceiptInfoMapper;
import com.io.hydralisk.util.CommonUtils;
import com.io.hydralisk.vo.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 收货地址
 */
@RequestMapping("/user_address")
@RestController
public class AddressController {

    @Resource
    private ReceiptInfoMapper receiptInfoMapper;

    @GetMapping("/my")
    public Object userAddress() {
        List<ReceiptInfo> receiptInfos = receiptInfoMapper.selectList(null);
        List<AddressVO> objects = new ArrayList<>();
        objects.add(AddressVO.getDefault());
        Map  data = CommonUtils.ofMap("list", objects, "pagelist", false, "rscount", 1, "url", "/index.php?m=user_address&a=default", "dist_list", null);
        Map rtnData = CommonUtils.ofMap("error", 0, "message", "success", "data", data, "url", "http://localhost:8080/h5/pages/user_address/my");
        return rtnData;
    }
    @PostMapping("/add")
    public Object add(AddressDTO addressDTO) {
        Map  data = CommonUtils.ofMap("data", null);
        Map rtnData = CommonUtils.ofMap("error", 0, "message", "success", "data", data, "url", "http://localhost:8080/h5/pages/user_address/add");
        return null;
    }
    @PostMapping("/save")
    public Object save(AddressDTO addressDTO) {

        Map  data = CommonUtils.ofMap("data", null);
        Map rtnData = CommonUtils.ofMap("error", 0, "message", "success", "data", data, "url", "http://localhost:8080/h5/pages/user_address/add");
        return null;
    }

}
