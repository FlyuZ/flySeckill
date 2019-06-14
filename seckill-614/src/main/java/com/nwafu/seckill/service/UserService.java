package com.nwafu.seckill.service;

import com.nwafu.seckill.entity.*;
import com.nwafu.seckill.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    public void updateUser(String username, String nickname, String mailbox) {
        userMapper.update(nickname, mailbox, username);
    }

    public User findByName(String username) {
        return userMapper.getByName(username);
    }

    public void inserIcon(String filename, int userId) {
      //  userMapper.addIcon(filename, userId);
    }

    //评论
    public List<Comment> findUserComment(int userId) {
        return commentMapper.findByUserId(userId);
    }

    //订单
    public List<SeckillOrder> findUserOrder(int userId) {
        return seckillOrderMapper.findByUserId(userId);
    }

    //查看所有收藏
    public List<Goods> findUserCollect(int userId) {
        List<Collect> collects = collectMapper.findByUserId(userId);
        List<Goods> goodsList = new ArrayList<>();
        for(Collect collect : collects){
            goodsList.add(goodsMapper.findById(collect.getGoodsId()));
        }
        return goodsList;
    }
}
