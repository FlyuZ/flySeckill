package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.Category;

import java.util.List;

public interface CategoryMapper {

    /**
     * 获取所有分类
     */
    List<Category> findAll();
}