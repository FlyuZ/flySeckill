package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface GoodsMapper {
    /**
     * 查询所有商品的信息
     */
    @Select("SELECT * FROM goods")
    List<Goods> findAll();

    /**
     * 查询某一分类ID的所有秒杀商品的记录信息
     */
    @Select("SELECT * FROM goods WHERE category_id = #{categoryId}")
    List<Goods> findByCategory(@Param("categoryId") int categoryId);

    /**
     * 根据主键查询当前秒杀商品的数据
     */
    @Select("SELECT * FROM goods WHERE goods_id = #{goodsid}")
    Goods findById(@Param("goodsid") int goodsid);

    /**
     * 减库存。
     *
     * @return 返回此SQL更新的记录数，如果>=1表示更新成功
     */
    @Update(" UPDATE goods\n" +
            "        SET stock_count = stock_count - 1\n" +
            "        WHERE goods_id = #{goodsId}\n" +
            "        AND start_time < #{killTime}\n" +
            "        AND end_time > #{killTime}\n" +
            "        AND stock_count > 0")
    int reduceStock(@Param("goodsId") int goodsId, @Param("killTime") Date killTime);
}
