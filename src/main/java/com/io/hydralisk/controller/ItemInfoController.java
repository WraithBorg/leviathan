package com.io.hydralisk.controller;

import com.io.hydralisk.convert.ItemInfoConvert;
import com.io.hydralisk.domain.CategoryInfo;
import com.io.hydralisk.domain.ItemInfo;
import com.io.hydralisk.mapper.CategoryInfoMapper;
import com.io.hydralisk.mapper.ItemInfoMapper;
import com.io.hydralisk.util.CommonUtils;
import com.io.hydralisk.vo.ItemInfoVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping("/item")
@RestController
public class ItemInfoController {
    @Resource
    private ItemInfoMapper itemInfoMapper;
    @Resource
    private CategoryInfoMapper  categoryInfoMapper;
    @Resource
    private ItemInfoConvert itemInfoConvert;

    @RequestMapping("/b2c_product")
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
}
