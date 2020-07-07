package com.io.hydralisk.controller;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.convert.KeFuLogConvert;
import com.io.hydralisk.domain.KeFuLogInfo;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.dto.KeFuLogDTO;
import com.io.hydralisk.mapper.KeFuLogMapper;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.service.usb.KeFuLogService;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.vo.KeFuLogVO;
import com.io.hydralisk.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客服
 */
@RequestMapping("/kefu")
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
    @GetMapping("/mykefu")
    public Object mykefu() {
        List<KeFuLogInfo> list = keFuLogService.getMyLog();
        List<KeFuLogVO> vos = keFuLogConvert.getKeFuLogVOS(list);

        Map data = CCommonUtils.ofMap("list", vos);
        return new ResultVO(data, CConstant.WEB_HOST + "/h5/pages/kefu/index");
    }

    /**
     * 保存客服记录
     */
    @PostMapping("/savelog")
    public Object saveLog(KeFuLogDTO keFuLogDTO) {
        UserInfo defaultUser = userInfoMapper.getDefaultUser();
        KeFuLogInfo info = new KeFuLogInfo();
        info.setUserId(defaultUser.getId());
        info.setCreateTime(new Date());
        info.setStatus(1);
        info.setContent(keFuLogDTO.getContent());
        keFuLogMapper.insert(info);
        return new ResultVO(new ArrayList<>(), CConstant.WEB_HOST + "/h5/pages/kefu/index");
    }

}

