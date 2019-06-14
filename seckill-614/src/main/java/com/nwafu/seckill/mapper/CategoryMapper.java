package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CategoryMapper {

    /**
     * 获取所有分类
     */
    @Select("SELECT * FROM category")
    List<Category> findAll();
}