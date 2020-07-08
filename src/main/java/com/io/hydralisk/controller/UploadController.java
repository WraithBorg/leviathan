package com.io.hydralisk.controller;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.constant.PageConst;
import com.io.hydralisk.convert.UserInfoConvert;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.service.usb.UserInfoService;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.vo.UserPassVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * 上传附件
 */
@RestController
public class UploadController {
    @Resource
    private HttpServletRequest httpServletRequest;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private UserInfoConvert userInfoConvert;

    /**
     * 上传用户头像
     */
    @PostMapping("/upload/img/user_head")
    public MsgResult uploadUserHead(@RequestParam("upimg") MultipartFile file) {
        uploadHeadImg(file);
        UserInfo defaultUser = userInfoService.getDefaultUser();
        UserPassVO userPassVO = userInfoConvert.getUserPassVO(defaultUser);
        Map data = CCommonUtils.ofMap("data", userPassVO);
        return MsgResult.doneUrl(data, PageConst.USER_PWD_SHOW);
    }

    /**
     * 上传用户头像
     */
    @PostMapping("/upload/img/user_head_save")
    public MsgResult user_head_save(@RequestParam("upimg") MultipartFile file) {
        uploadHeadImg(file);
        UserInfo defaultUser = userInfoService.getDefaultUser();
        UserPassVO userPassVO = userInfoConvert.getUserPassVO(defaultUser);
        Map data = CCommonUtils.ofMap("data", userPassVO);
        return MsgResult.doneUrl(data, PageConst.USER_PWD_SHOW);
    }

    /**
     * 上出图片
     */
    private void uploadHeadImg(@RequestParam("upimg") MultipartFile file) {
        try {
            String headFileName = UUID.randomUUID().toString() + file.getOriginalFilename();
            file.transferTo(new File(headFileName));
            copyFile(headFileName);//复制小比例图片
            // TODO 删除旧图片
            UserInfo defaultUser = userInfoMapper.getDefaultUser();
            defaultUser.setHeadImgUrl(headFileName);
            userInfoMapper.updateById(defaultUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制压缩100*100图片
     */
    private void copyFile(String fileName) {
        File file = new File(CConstant.LOCAL_HEAD_PATH + fileName);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] bit = new byte[fis.available()];
            fis.read(bit);
            File file2 = new File(CConstant.LOCAL_HEAD_PATH + fileName + ".100x100.jpg");
            FileOutputStream fos = new FileOutputStream(file2);
            fos.write(bit);
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
