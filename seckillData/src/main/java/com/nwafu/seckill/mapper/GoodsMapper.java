package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.Goods;

import java.util.List;

public interface GoodsMapper {
    /**
     * 查询所有商品的信息
     */
    List<Goods> findAll();

    /**
     * 查询某一分类ID的所有秒杀商品的记录信息
     */
    List<Goods> findByCategory();

    /**
     * 根据主键查询当前秒杀商品的数据
     */
    Goods findById();

    /**
     * 减库存。
     * @return 返回此SQL更新的记录数，如果>=1表示更新成功
     */
    int reduceStock();
}