package com.nwafu.seckill.service;

import com.nwafu.seckill.entity.Collect;
import com.nwafu.seckill.entity.Comment;
import com.nwafu.seckill.entity.SeckillOrder;
import com.nwafu.seckill.entity.User;
import com.nwafu.seckill.mapper.CollectMapper;
import com.nwafu.seckill.mapper.CommentMapper;
import com.nwafu.seckill.mapper.SeckillOrderMapper;
import com.nwafu.seckill.mapper.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

    public void updateUser(String username,String nickname,String mailbox){userMapper.update(nickname,mailbox,username);}

    public User findByName(String username){
        return userMapper.getByName(username);
    }
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
