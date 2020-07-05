package com.io.hydralisk.controller;

import com.io.hydralisk.convert.CategoryInfoConvert;
import com.io.hydralisk.domain.CategoryInfo;
import com.io.hydralisk.mapper.CategoryInfoMapper;
import com.io.hydralisk.util.CommonUtils;
import com.io.hydralisk.vo.CategoryVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping("/category")

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

        Map  data = CommonUtils.ofMap("error", 0, "message", "success", "catList", categoryVOS, "url", "http://localhost:8081/h5/pageb2c/b2c_category/index");
        Map  rtnData = CommonUtils.ofMap("data", data);
        return rtnData;
    }
}