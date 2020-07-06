package com.io.hydralisk.controller;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.convert.ReceiptInfoConvert;
import com.io.hydralisk.domain.ReceiptInfo;
import com.io.hydralisk.dto.ReceiptInfoDTO;
import com.io.hydralisk.service.usb.ReceiptInfoService;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.vo.ReceiptInfoVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 收货地址
 */
@RequestMapping("/user_address")
@RestController
public class ReceiptInfoController {


    @Resource
    private ReceiptInfoConvert receiptConvert;
    @Resource
    private ReceiptInfoService receiptInfoService;

    /**
     * 查询收货地址列表
     */
    @GetMapping("/my")
    public Object userAddress() {
        List<ReceiptInfo> receiptInfos = receiptInfoService.selectList(null);
        List<ReceiptInfoVO> receiptVOS = receiptConvert.getReceiptVOS(receiptInfos);
        Map data = CCommonUtils.ofMap("list", receiptVOS, "pagelist", false, "rscount", 1, "url", "/index.php?m=user_address&a=default", "dist_list", null);
        Map rtnData = CCommonUtils.ofMap("error", 0, "message", "success", "data", data, "url", CConstant.WEB_HOST+"/h5/pages/user_address/my");
        return rtnData;
    }

    /**
     * 点击新增按钮
     */
    @GetMapping("/add")
    public Object add(@RequestParam(required = false) String id) {
        if (CCommonUtils.isBlank(id)) {
            Map data = CCommonUtils.ofMap("data", null);
            Map rtnData = CCommonUtils.ofMap("error", 0, "message", "success", "data", data, "url", CConstant.WEB_HOST+"/h5/pages/user_address/add");
            return rtnData;
        }
        ReceiptInfo receiptInfo = receiptInfoService.selectById(id);
        ReceiptInfoVO receiptVO = receiptConvert.getReceiptVO(receiptInfo);
        Map data = CCommonUtils.ofMap("data", receiptVO);
        Map rtnData = CCommonUtils.ofMap("error", 0, "message", "success", "data", data, "url", CConstant.WEB_HOST+"/h5/pages/user_address/add");
        return rtnData;

    }

    /**
     * 保存收货地址
     */
    @PostMapping("/save")
    public Object save(ReceiptInfoDTO addressDTO) {
        if (addressDTO.getId() == null) {
            ReceiptInfo receiptInfo = receiptConvert.getReceiptInfo(addressDTO);
            receiptInfoService.insert(receiptInfo);
            Map data = CCommonUtils.ofMap("data", null);
            Map rtnData = CCommonUtils.ofMap("error", 0, "message", "success", "data", data, "url", CConstant.WEB_HOST+"/h5/pages/user_address/add");
            return rtnData;
        }
        ReceiptInfo receiptInfo = receiptConvert.getReceiptInfo(addressDTO);
        receiptInfoService.updateById(receiptInfo);

        Map data = CCommonUtils.ofMap("data", null);
        Map rtnData = CCommonUtils.ofMap("error", 0, "message", "success", "data", data, "url", CConstant.WEB_HOST+"/h5/pages/user_address/add");
        return rtnData;
    }

    @GetMapping("/delete")
    public Object delete(@RequestParam(required = false) String id) {
        receiptInfoService.deleteById(id);
        return CCommonUtils.ofMap("error", 0, "message", "删除成功");
    }

}
