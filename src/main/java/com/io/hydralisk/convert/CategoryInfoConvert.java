package com.io.hydralisk.convert;

import com.io.hydralisk.constant.CConstant;
import com.io.hydralisk.domain.CategoryInfo;
import com.io.hydralisk.util.CCommonUtils;
import com.io.hydralisk.vo.CategoryVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品类别
 */
@Component
public class CategoryInfoConvert {
    private final String finalFatherID = "0";//父类的pid
    public List<CategoryVO> getCategoryVOS(List<CategoryInfo> categoryInfos) {
        List<CategoryVO> categoryVOS = categoryInfos.stream().map(this::getCategoryVO).collect(Collectors.toList());
        List<CategoryVO> bigCats = categoryVOS.stream().filter(m -> m.getPid().equals(finalFatherID)).collect(Collectors.toList());
        for (CategoryVO categoryVO : bigCats) {
            List<CategoryVO> childList = categoryVOS.stream().filter(m -> m.getPid().equals(categoryVO.getCatid())).collect(Collectors.toList());
            categoryVO.setChild(childList);
        }
        return bigCats;
    }

    private CategoryVO getCategoryVO(CategoryInfo categoryInfo) {
        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setCatid(categoryInfo.getId());
        categoryVO.setDescription(categoryInfo.getName());
        categoryVO.setEx_table_id(0L);

        if (CCommonUtils.isNotBlank(categoryInfo.getImgUrl())){
            categoryVO.setImgurl(CConstant.IMAGE_HOST+categoryInfo.getImgUrl());
        }

        categoryVO.setOrderindex(0);
        categoryVO.setPid(categoryInfo.getPid());
        categoryVO.setStatus(1);
        categoryVO.setTitle(categoryInfo.getName());
        return categoryVO;
    }
}
