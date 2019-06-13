package com.nwafu.seckill.service;

import com.nwafu.seckill.entity.Collect;
import com.nwafu.seckill.entity.Comment;
import com.nwafu.seckill.entity.SeckillOrder;
import com.nwafu.seckill.mapper.CollectMapper;
import com.nwafu.seckill.mapper.CommentMapper;
import com.nwafu.seckill.mapper.SeckillOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Autowired
    private CollectMapper collectMapper;
    //评论
    public List<Comment> findUserComment(int userId) {
        return commentMapper.findByUserId(userId);
    }

    //订单
    public List<SeckillOrder> findUserOrder(int userId) {
        return seckillOrderMapper.findByUserId(userId);
    }
    //收藏
    public List<Collect> findUserCollect(int userId){
        return collectMapper.findByUserId(userId);
    }
}
