package com.io.hydralisk.controller;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.convert.CategoryInfoConvert;
import com.io.hydralisk.convert.ItemInfoConvert;
import com.io.hydralisk.domain.CategoryInfo;
import com.io.hydralisk.domain.ItemInfo;
import com.io.hydralisk.domain.ItemInfoImg;
import com.io.hydralisk.mapper.CategoryInfoMapper;
import com.io.hydralisk.mapper.ItemInfoImgMapper;
import com.io.hydralisk.mapper.ItemInfoMapper;
import com.io.hydralisk.util.CommonUtils;
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

@RequestMapping("item/b2c_product")
@RestController
public class ItemInfoController {
    @Resource
    private ItemInfoMapper itemInfoMapper;
    @Resource
    private CategoryInfoMapper  categoryInfoMapper;
    @Resource
    private ItemInfoConvert itemInfoConvert;
    @Resource
    private CategoryInfoMapper categoryMapper;
    @Resource
    private CategoryInfoConvert categoryInfoConvert;
    @Resource
    private ItemInfoImgMapper itemInfoImgMapper;
    /**
     * 商品列表
     */
    @RequestMapping("/list")
    public Object list(@RequestParam(required = false) String orderby,
                       @RequestParam(required = false) String catid) {
        List<ItemInfo> itemInfos  ;

        // category
        Map catMap = null;
        if (CommonUtils.isNotBlank(catid)){
            CategoryInfo catDB = categoryInfoMapper.selectById(catid);
            catMap = CommonUtils.ofMap("catid", catDB.getId(), "pid", catDB.getPid(), "title", catDB.getName());
            itemInfos = itemInfoMapper.selectByMap(CommonUtils.ofMap("category_id", catid));
        }else {
            itemInfos = itemInfoMapper.selectList(null);
        }
        itemInfos.forEach(m -> {
            List<ItemInfoImg> itemInfoImgs = itemInfoImgMapper.selectByMap(CommonUtils.ofMap("item_id", m.getId(), "default_flag", "1"));
            if (itemInfoImgs.size() > 0){
                m.setDefaultImg(itemInfoImgs.get(0).getUrl());
            }
        });
        List<ItemInfoVO> list = itemInfoConvert.getItemInfoVOS(itemInfos);

        // category
        Map  data = CommonUtils.ofMapN(
                "cat", catMap,
                "catList","",
                "list",list,
                "pagelist",false,
                "per_page",0,
                "rscount",3,
                "url","/module.php?m=b2c_product&a=list");
        Map<String, Object> rtnMap = CommonUtils.ofMap("error", 0, "message", "success", "data", data, "url", "http://localhost:8081/h5/pageb2c/b2c_product/list?catid=" + catid);
        return rtnMap;
    }

    /**
     * 相似商品
     */
    @RequestMapping("/likelist")
    public Object likelist(@RequestParam String productid) {
        //
        List<CategoryInfo> categoryInfos = categoryMapper.selectList(null);
        List<CategoryVO> categoryVOS = categoryInfoConvert.getCategoryVOS(categoryInfos);
        //
        List<ItemInfo> itemInfos = itemInfoMapper.selectList(null);
        itemInfos.forEach(m -> {
            List<ItemInfoImg> itemInfoImgs = itemInfoImgMapper.selectByMap(CommonUtils.ofMap("item_id", m.getId(), "default_flag", "1"));
            if (itemInfoImgs.size() > 0){
                m.setDefaultImg(itemInfoImgs.get(0).getUrl());
            }
        });
        List<ItemInfoVO> list = itemInfoConvert.getItemInfoVOS(itemInfos);
        Map data = CommonUtils.ofMapN(
                "rscount", 11,
                "per_page", 0,
                "pagelist", false,
                "catList", categoryVOS,
                "list", list,
                "url", "/module.php?m=b2c_product&a=default");
        Map dataMap = CommonUtils.ofMap("error", 0, "message", "success", "data", data, "url", "http://localhost:8081/h5/pageb2c/b2c_product/show?id=" + productid);
        return dataMap;
    }
    /**
     * 显示商品
     */
    @RequestMapping("/show")
    public Object show(@RequestParam String id,@RequestParam String orderid) {
        ItemInfo itemInfo = itemInfoMapper.selectById(id);
        ItemInfoVO itemInfoVO = itemInfoConvert.getItemInfoVO(itemInfo);
        List<ItemInfoImg> itemInfoImgs = itemInfoImgMapper.selectByMap(CommonUtils.ofMap("item_id", id));
        List<String> imgsdata = itemInfoImgs.stream().map(m -> {
            if (CommonUtils.isBlank(m.getUrl())) {
                return null;
            }
            return CConstant.IMAGE_HOST + m.getUrl();
        }).collect(Collectors.toList());
        Map dataMap = CommonUtils.ofMapN(
                "cart_amount", 11,
                "data", itemInfoVO,
                "fieldsList", null,
                "imgsdata", imgsdata,
                "isfav", 0,
                "ksList", new ArrayList<>(),
                "ksList2", null,
                "ksid", 0,
                "order", false,
                "pts", false,
                "pts_num", 0,
                "sharePic", "https://kfbc.deitui.com//index.php?m=gd&a=ShareProduct&imgurl=https://kfbc-deitui-com.oss-cn-hangzhou.aliyuncs.com/attach/2020/04/17/61.jpg&title=2020%E7%99%BD%E6%AF%AB%E9%93%B6%E9%92%88-A01&price=1000.00&url=https%3A%2F%2Fkfbc.deitui.com%2F%2Fmodule.php%3Fm%3Db2c_product%26a%3Dshow%26id%3D160"
                 );
        Map rtnMap = CommonUtils.ofMap("error", 0, "message", "success", "data", dataMap, "url", "http://localhost:8080/h5/pageb2c/b2c_product/show?id=" + id);
        return rtnMap;
    }
    /**
     * Raty
     */
    @RequestMapping("/raty")
    public Object raty(@RequestParam String id,@RequestParam String limit) {
        Map dataMap = CommonUtils.ofMapN("list",new ArrayList<>(),"productid",id,"rscount",0);
        Map rtnMap = CommonUtils.ofMap("error", 0, "message", "success", "data", dataMap, "url", "http://localhost:8080/h5/pageb2c/b2c_product/show?id=" + id);
        return rtnMap;
    }
}
