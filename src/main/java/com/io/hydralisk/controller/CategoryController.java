package com.io.hydralisk.controller;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.convert.CategoryInfoConvert;
import com.io.hydralisk.domain.CategoryInfo;
import com.io.hydralisk.mapper.CategoryInfoMapper;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.vo.CategoryVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping("/category")
/**
 * 商品类别
 */
@RestController
public class CategoryController {
    @Resource
    private CategoryInfoMapper categoryMapper;
    @Resource
    private CategoryInfoConvert categoryInfoConvert;

    @RequestMapping("/b2c_category")
    public Object b2c_category() {
        List<CategoryInfo> categoryInfos = categoryMapper.selectList(null);
        List<CategoryVO> categoryVOS = categoryInfoConvert.getCategoryVOS(categoryInfos);

        Map  data = CCommonUtils.ofMap("error", 0, "message", "success", "catList", categoryVOS, "url", CConstant.WEB_HOST  + "/h5/pageb2c/b2c_category/index");
        Map  rtnData = CCommonUtils.ofMap("data", data);
        return rtnData;
    }
}
