package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.Comment;

import java.util.List;

public interface CommentMapper {
    /**
     * 根据商品ID查评论
     */
    List<Comment> findByGoodsId();

    /**
     * 根据用户ID查评论
     */
    List<Comment> findByUserId();
    /**
     * 增加评论
     */
    int insert();

}