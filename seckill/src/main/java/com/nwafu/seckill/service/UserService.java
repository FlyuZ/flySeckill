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



    public Goods findGoods(int goodsId){
        return goodsMapper.findById(goodsId);
    }

    //评论
    public List<CommentTemp> findUserComment(int userId) {
        List<Comment> comments = commentMapper.findByUserId(userId);
        List<CommentTemp> commentTemps = new ArrayList<>();
        for(Comment comment : comments){
            Goods goods = goodsMapper.findById(comment.getGoodsId());
            commentTemps.add(new CommentTemp(comment.getGoodsId(), comment.getUserId(),comment.getCommentText(), goods.getGoodsName(), goods.getImage(), null));
        }
        return commentTemps;
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

    /**
     * 用户插入评论
     *
     * @return
     */
    public int insertComment(int goods, int userId, String commentText){
        return commentMapper.insert(goods, userId, commentText);
    }
}
