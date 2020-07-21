package com.io.hydralisk.controller;

import com.io.hydralisk.annotate.WithoutLogin;
import com.io.hydralisk.constant.PageConst;
import com.io.hydralisk.convert.CategoryInfoConvert;
import com.io.hydralisk.convert.ItemInfoConvert;
import com.io.hydralisk.domain.CategoryInfo;
import com.io.hydralisk.domain.ItemInfo;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.mapper.CategoryInfoMapper;
import com.io.hydralisk.mapper.ItemInfoMapper;
import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.service.usb.ItemInfoService;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.util.SessionUtil;
import com.io.hydralisk.vo.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 首页
 */
@RestController
public class IndexController {
    @Resource
    private HttpServletRequest httpServletRequest;
    @Resource
    private ItemInfoService itemInfoService;
    @Resource
    private ItemInfoConvert itemInfoConvert;
    @Resource
    private ItemInfoMapper itemInfoMapper;
    @Resource
    private CategoryInfoConvert categoryInfoConvert;
    @Resource
    private CategoryInfoMapper categoryMapper;
    /**
     * 首页查询信息集合
     */
    @WithoutLogin
    @GetMapping("b2c/indexpage")
    public MsgResult indexpage() {
        UserInfo currentUser = SessionUtil.getCurrentUser(httpServletRequest);
        List adList = new ArrayList<>();
        List<ItemInfo> itemInfos = itemInfoService.selectListWithImg(itemInfoMapper.selectBiMaiList());
        // 必买好货
        List<ItemInfo4IndexVO> bmList = itemInfoConvert.getItemInfo4IndexVOS(itemInfos);
        // 首页轮播图
        List<Flash4IndexVO> flashList = Stream.of(new Flash4IndexVO()).collect(Collectors.toList());
        // 热销商品
        List<ItemInfoVO> hotList = itemInfoConvert.getItemInfoVOS( currentUser,itemInfoMapper.selectHotSaleList());
        // 热销商品类型
        List<CategoryInfo> categoryInfos = categoryMapper.getCats4Index();
        List<Category4IndexVO> navList = categoryInfoConvert.getCategory4IndexVOS(categoryInfos);
        //
        List<ItemInfoVO> recList = hotList;
        // 网站信息
        Site4IndexVO site = new Site4IndexVO();
        //
        Map<Object, Object> data = CCommonUtils.ofMapN(
                "adList", adList
                , "bmList", bmList//首页：必买好货
                , "flashList", flashList//首页：轮播图
                , "hotList", hotList
                , "navList", navList
                , "recList", recList
                , "site", site
        );
        return MsgResult.done(data, PageConst.INDEX);
    }
}
