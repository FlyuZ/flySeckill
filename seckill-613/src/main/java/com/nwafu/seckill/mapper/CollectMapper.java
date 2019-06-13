package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.Collect;
import com.nwafu.seckill.entity.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Insert("INSERT INTO collent (goods_id, user_id) VALUE (#{goodsId}, #{userId})")
    int insertCollect(@Param("goodsId") int goodsId, @Param("userId") int userId);

    /**
     * 删除收藏
     */
    void delete();
}