package com.io.hydralisk.controller;

import com.io.hydralisk.annotate.WithoutLogin;
import com.io.hydralisk.constant.PageConst;
import com.io.hydralisk.convert.CategoryInfoConvert;
import com.io.hydralisk.domain.CategoryInfo;
import com.io.hydralisk.mapper.CategoryInfoMapper;
import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.vo.CategoryVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商品类别
 */
@RestController
public class CategoryController {
    @Resource
    private HttpServletRequest httpServletRequest;
    @Resource
    private CategoryInfoMapper categoryMapper;
    @Resource
    private CategoryInfoConvert categoryInfoConvert;

    /**
     * 查询品项类别树
     */
    @WithoutLogin
    @RequestMapping("/category/b2c_category_tree")
    public MsgResult b2c_category() {
        List<CategoryInfo> categoryInfos = categoryMapper.selectList(null);
        List<CategoryVO> categoryVOS = categoryInfoConvert.getCategoryVOS(categoryInfos);
        System.out.println(PageConst.WEB_HOST);
        return MsgResult.doneUrl("catList", categoryVOS, PageConst.CATEGORY_INDEX);
    }
}
