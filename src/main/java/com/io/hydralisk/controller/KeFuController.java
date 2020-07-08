package com.io.hydralisk.controller;

import com.io.hydralisk.constant.PageConst;
import com.io.hydralisk.convert.KeFuLogConvert;
import com.io.hydralisk.domain.KeFuLogInfo;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.dto.KeFuLogDTO;
import com.io.hydralisk.mapper.KeFuLogMapper;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.service.usb.KeFuLogService;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.vo.KeFuLogVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客服
 */

@RestController
public class KeFuController {
    @Resource
    private KeFuLogConvert keFuLogConvert;
    @Resource
    private KeFuLogMapper keFuLogMapper;
    @Resource
    private KeFuLogService keFuLogService;
    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 获取客服记录
     */
    @GetMapping("/kefu/mykefu")
    public MsgResult mykefu() {
        List<KeFuLogInfo> list = keFuLogService.getMyLog();
        List<KeFuLogVO> vos = keFuLogConvert.getKeFuLogVOS(list);
        Map data = CCommonUtils.ofMap("list", vos);
        return MsgResult.doneUrl(data, PageConst.KEFU_INDEX);
    }

    /**
     * 保存客服记录
     */
    @PostMapping("/kefu/savelog")
    public Object MsgResult(KeFuLogDTO keFuLogDTO) {
        UserInfo defaultUser = userInfoMapper.getDefaultUser();
        KeFuLogInfo info = new KeFuLogInfo();
        info.setUserId(defaultUser.getId());
        info.setCreateTime(new Date());
        info.setStatus(1);
        info.setContent(keFuLogDTO.getContent());
        keFuLogMapper.insert(info);
        return MsgResult.doneUrl(new ArrayList<>(), PageConst.KEFU_INDEX);
    }

}

