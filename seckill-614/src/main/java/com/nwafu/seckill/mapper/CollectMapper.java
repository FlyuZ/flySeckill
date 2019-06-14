package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.Collect;
import com.nwafu.seckill.entity.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CollectMapper {
    /**
     * 根据用户ID查所有收藏商品信息
     */
    @Select("SECLECT * FROM collect WHERE user_id = #{userId}")
    List<Collect> findByUserId(@Param("userId") int userId);

    /**
     * 插入收藏
     */
    @Insert("INSERT INTO collect (goods_id, user_id) VALUE (#{goodsId}, #{userId})")
    int insertCollect(@Param("goodsId") int goodsId, @Param("userId") int userId);

    /**
     * 删除收藏
     */
    @Delete("DELETE FROM collect WHERE goods_id = #{goodsId} AND user_id = #{userId}")
    void delete(@Param("goodsId") int goodsId, @Param("userId") int userId);

    /**
     *判断是否收藏
     */
    @Select("select count(*) from collect where user_id = #{userId} AND goods_id = #{goodsId}")
    int findExist(@Param("goodsId") int goodsId, @Param("userId") int userId);
}