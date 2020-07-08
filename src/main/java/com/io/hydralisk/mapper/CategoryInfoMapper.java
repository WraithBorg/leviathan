package com.io.hydralisk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.io.hydralisk.domain.CategoryInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryInfoMapper extends BaseMapper<CategoryInfo> {

    @Select(" select * from category_info limit 5 ")
    List<CategoryInfo> getCats4Index();
}
