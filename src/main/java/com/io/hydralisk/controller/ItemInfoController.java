package com.io.hydralisk.controller;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.constant.PageConst;
import com.io.hydralisk.convert.CategoryInfoConvert;
import com.io.hydralisk.convert.ItemInfoConvert;
import com.io.hydralisk.domain.CategoryInfo;
import com.io.hydralisk.domain.ItemInfo;
import com.io.hydralisk.domain.ItemInfoImg;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.mapper.CategoryInfoMapper;
import com.io.hydralisk.mapper.ItemInfoImgMapper;
import com.io.hydralisk.mapper.ItemInfoMapper;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.service.usb.ItemInfoService;
import com.io.hydralisk.service.usb.UserFavItemService;
import com.io.hydralisk.service.usb.UserInfoService;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.vo.CategoryVO;
import com.io.hydralisk.vo.ItemInfoVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ItemInfoController {
    @Resource
    private ItemInfoMapper itemInfoMapper;
    @Resource
    private CategoryInfoMapper categoryInfoMapper;
    @Resource
    private ItemInfoConvert itemInfoConvert;
    @Resource
    private CategoryInfoMapper categoryMapper;
    @Resource
    private CategoryInfoConvert categoryInfoConvert;
    @Resource
    private ItemInfoImgMapper itemInfoImgMapper;
    @Resource
    private ItemInfoService infoService;
    @Resource
    private UserFavItemService userFavInfoService;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private ItemInfoService itemInfoService;
    /**
     * 商品列表
     */
    @RequestMapping("item/b2c_product/list")
    public MsgResult list(@RequestParam(required = false) String orderby,
                          @RequestParam(required = false) String catid) {
        List<ItemInfo> itemInfos;

        // category
        Map catMap = null;
        if (CCommonUtils.isNotBlank(catid)) {
            CategoryInfo catDB = categoryInfoMapper.selectById(catid);
            catMap = CCommonUtils.ofMap("catid", catDB.getId(), "pid", catDB.getPid(), "title", catDB.getName());
            itemInfos = itemInfoMapper.selectByMap(CCommonUtils.ofMap(ItemInfo.t.category_id, catid));
        } else {
            itemInfos = itemInfoMapper.selectList(null);
        }
        itemInfos.forEach(m -> {
            List<ItemInfoImg> itemInfoImgs = itemInfoImgMapper.selectByMap(CCommonUtils.ofMap(ItemInfoImg.t.item_id, m.getId(), ItemInfoImg.t.default_flag, "1"));
            if (itemInfoImgs.size() > 0) {
                m.setDefaultImg(itemInfoImgs.get(0).getUrl());
            }
        });
        List<ItemInfoVO> list = itemInfoConvert.getItemInfoVOS(itemInfos);

        // category
        Map data = CCommonUtils.ofMapN(
                "cat", catMap,
                "catList", "",
                "list", list,
                "pagelist", false,
                "per_page", 0,
                "rscount", 0
        );
        return MsgResult.doneUrl(data, PageConst.PRODUCT_LIST_4CATEGORY + catid);
    }

    /**
     * 相似商品
     */
    @RequestMapping("item/b2c_product/likelist")
    public MsgResult likelist(@RequestParam String productid) {
        //
        List<CategoryInfo> categoryInfos = categoryMapper.selectList(null);
        List<CategoryVO> categoryVOS = categoryInfoConvert.getCategoryVOS(categoryInfos);
        //
        List<ItemInfo> itemInfos = itemInfoService.selectListWithImg();
        List<ItemInfoVO> list = itemInfoConvert.getItemInfoVOS(itemInfos);
        Map data = CCommonUtils.ofMapN(
                "rscount", 0,
                "per_page", 0,
                "pagelist", false,
                "catList", categoryVOS,
                "list", list);
        return MsgResult.doneUrl(data,PageConst.PRODUCT_SHOW + productid);
    }

    /**
     * 显示商品
     */
    @RequestMapping("item/b2c_product/show")
    public MsgResult show(@RequestParam String id, @RequestParam String orderid) {

        ItemInfo itemInfo = infoService.getItemWithImg(id);
        ItemInfoVO itemInfoVO = itemInfoConvert.getItemInfoVO(itemInfo);

        List<ItemInfoImg> itemInfoImgs = itemInfoImgMapper.selectByMap(CCommonUtils.ofMap(ItemInfoImg.t.item_id, id));
        List<String> imgsdata = itemInfoImgs.stream().map(m -> {
            if (CCommonUtils.isBlank(m.getUrl())) {
                return null;
            }
            return CConstant.IMAGE_HOST + m.getUrl();
        }).collect(Collectors.toList());
        // 查看是否该商品是否在收藏夹里
        UserInfo defaultUser = userInfoMapper.getDefaultUser();
        Integer hasFav = userFavInfoService.hasFav(defaultUser.getId(), itemInfo.getId());

        Map dataMap = CCommonUtils.ofMapN(
                "cart_amount", itemInfoVO.getCart_amount(),
                "data", itemInfoVO,
                "fieldsList", null,
                "imgsdata", imgsdata,
                "isfav", hasFav,//收藏
                "ksList", new ArrayList<>(),
                "ksList2", null,
                "ksid", 0,
                "order", false,
                "pts", false,
                "pts_num", 0,
                "sharePic",
                ""
        );
        return MsgResult.doneUrl(dataMap,PageConst.PRODUCT_SHOW+ id);
    }

    /**
     * 商品评价列表
     * Raty
     */
    @RequestMapping("item/b2c_product/raty")
    public MsgResult raty(@RequestParam String id, @RequestParam String limit) {
        ArrayList<Object> list = new ArrayList<>();
        UserInfo userInfo = userInfoMapper.getDefaultUser();
        list.add(CCommonUtils.ofMap("user_head", CConstant.DEFAULT_HEAD_URL +userInfo.getHeadImgUrl(),
                "nickname", "张三",
                "raty_grade", "3",
                "raty_content", "这玩意真好" ));
        list.add(CCommonUtils.ofMap("user_head", CConstant.DEFAULT_HEAD_URL +userInfo.getHeadImgUrl(),
                "nickname", "李四",
                "raty_grade", "3",
                "raty_content", "这玩意真垃圾" ));
        Map dataMap = CCommonUtils.ofMapN("list", list, "productid", id, "rscount", 2);
        return  MsgResult.doneUrl(dataMap,PageConst.PRODUCT_SHOW+ id);
    }

    /**
     * 相似商品
     */
    @RequestMapping("item/b2c_product/reclist")
    public MsgResult reclist(@RequestParam String productid) {
        //
        List<CategoryInfo> categoryInfos = categoryMapper.selectList(null);
        List<CategoryVO> categoryVOS = categoryInfoConvert.getCategoryVOS(categoryInfos);
        //
        List<ItemInfo> itemInfos = itemInfoService.selectListWithImg();
        List<ItemInfoVO> list = itemInfoConvert.getItemInfoVOS(itemInfos);
        Map data = CCommonUtils.ofMapN(
                "rscount", list.size(),
                "per_page", 0,
                "pagelist", false,
                "catList", categoryVOS,
                "list", list);
        return MsgResult.doneUrl(data,PageConst.PRODUCT_SHOW+ productid);
    }
}
