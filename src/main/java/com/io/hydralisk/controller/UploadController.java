package com.io.hydralisk.controller;

import com.io.hydralisk.chip.MultipartContext;
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
            UserInfo defaultUser = userInfoMapper.getDefaultUser();
            String headFileName = UUID.randomUUID().toString() + file.getOriginalFilename();
            File newFiel = new File(headFileName);
            file.transferTo(newFiel);
            copyFilex100(MultipartContext.multipartLocation + File.separator + headFileName);
            deleteOldFile(defaultUser.getHeadImgUrl());
            defaultUser.setHeadImgUrl("upload/"+headFileName);
            userInfoMapper.updateById(defaultUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除旧头像
     */
    private void deleteOldFile(String imgUrl) {
        String oldImgPath = MultipartContext.resourceLocation + imgUrl;
        File oldImg = new File(oldImgPath);
        if (oldImg.exists()&&oldImg.isFile()){
            oldImg.delete();
        }

        String oldImgPathx100 = oldImgPath + ".100x100.jpg";
        File oldImgx100 = new File(oldImgPathx100);
        if (oldImgx100.exists()&&oldImgx100.isFile()){
            oldImgx100.delete();
        }
    }

    /**
     * 复制压缩100*100图片
     */
    private void copyFilex100(String filePathName) {
        File file = new File(filePathName);
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            byte[] bit = new byte[fis.available()];
            fis.read(bit);
            File file2 = new File(filePathName + ".100x100.jpg");
            FileOutputStream fos = new FileOutputStream(file2);
            fos.write(bit);
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
