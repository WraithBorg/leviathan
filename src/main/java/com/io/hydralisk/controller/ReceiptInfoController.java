package com.io.hydralisk.controller;

import com.io.hydralisk.constant.PageConst;
import com.io.hydralisk.convert.ReceiptInfoConvert;
import com.io.hydralisk.domain.ReceiptInfo;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.dto.ReceiptInfoDTO;
import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.service.usb.ReceiptInfoService;
import com.io.hydralisk.service.usb.UserInfoService;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.util.SessionUtil;
import com.io.hydralisk.vo.ReceiptInfoVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 收货地址
 */
@RestController
public class ReceiptInfoController {
    @Resource
    private HttpServletRequest httpServletRequest;
    @Resource
    private ReceiptInfoConvert receiptConvert;
    @Resource
    private ReceiptInfoService receiptInfoService;
    @Resource
    private UserInfoService userInfoService;

    /**
     * 查询收货地址列表
     */
    @GetMapping("/user_address/my")
    public MsgResult userAddress() {
        //
        UserInfo defaultUser = SessionUtil.getCurrentUser(httpServletRequest);
        List<ReceiptInfo> receiptInfos = receiptInfoService.selectList(defaultUser.getId());
        List<ReceiptInfoVO> receiptVOS = receiptConvert.getReceiptVOS(receiptInfos);
        Map data = CCommonUtils.ofMap("list", receiptVOS,
                "pagelist", false,
                "rscount", receiptVOS.size(),
                "url", "/index.php?m=user_address&a=default",
                "dist_list", null);
        return MsgResult.doneUrl(data, PageConst.ADDRESS_ADD);
    }

    /**
     * 点击新增按钮
     */
    @GetMapping("/user_address/add")
    public MsgResult add(@RequestParam(required = false) String id) {
        if (CCommonUtils.isBlank(id)) {
            Map data = CCommonUtils.ofMap("data", null);
            return MsgResult.doneUrl(data, PageConst.ADDRESS_ADD);
        }
        ReceiptInfo receiptInfo = receiptInfoService.selectById(id);
        ReceiptInfoVO receiptVO = receiptConvert.getReceiptVO(receiptInfo);
        Map data = CCommonUtils.ofMap("data", receiptVO);
        return MsgResult.doneUrl(data, PageConst.ADDRESS_ADD);
    }

    /**
     * 保存收货地址
     */
    @PostMapping("/user_address/save")
    public MsgResult save(ReceiptInfoDTO addressDTO) {
        UserInfo currentUser = SessionUtil.getCurrentUser(httpServletRequest);
        if (addressDTO.getId() == null) {
            ReceiptInfo receiptInfo = receiptConvert.getReceiptInfo(addressDTO);
            receiptInfoService.insert(currentUser,receiptInfo);
            Map data = CCommonUtils.ofMap("data", null);
            return MsgResult.doneUrl(data, PageConst.ADDRESS_ADD);
        }
        ReceiptInfo receiptInfo = receiptConvert.getReceiptInfo(addressDTO);
        receiptInfoService.updateById(receiptInfo);
        Map data = CCommonUtils.ofMap("data", null);
        return MsgResult.doneUrl(data, PageConst.ADDRESS_ADD);
    }
    /**
     * 删除收货地址
     */
    @GetMapping("/user_address/delete")
    public MsgResult delete(@RequestParam(required = false) String id) {
        receiptInfoService.deleteById(id);
        return MsgResult.doneMsg("删除成功");
    }

}
