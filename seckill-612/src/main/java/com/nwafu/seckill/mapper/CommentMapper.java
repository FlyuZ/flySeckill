package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {
    /**
     * 根据商品ID查评论
     */
    @Select("SELECT * FROM comment WHERE goods_id = #{goodsId}")
    List<Comment> findByGoodsId(@Param("goodsId") int goodsId);

    /**
     * 根据用户ID查评论
     */
    @Select("SELECT * FROM comment WHERE user_id = #{userId}")
    List<Comment> findByUserId(@Param("userId") int userId);

    /**
     * 增加评论
     */
    @Insert("INSERT INTO comment(goods_id, user_id, comment_text) VALUE (#{goodsId}, #{userId}, #{comment})")
    int insert(@Param("goodsId") int goodsId, @Param("userId") int userId, @Param("comment") String comment);

}