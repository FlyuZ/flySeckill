package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.Goods;

import java.util.List;

public interface CollectMapper {
    /**
     * 根据用户ID查所有收藏商品信息
     */
    List<Goods> findByUserId();

    /**
     * 删除收藏
     */
    void delete();
}