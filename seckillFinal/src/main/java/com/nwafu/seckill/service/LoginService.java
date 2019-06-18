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
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    //用户登录
    public User userLogin(String username, String password) {
        User user = userMapper.userlogin(username, password);
        return user;
    }

    //注册新用户
    public int adduser(String username, String password, String nickname) {
        return userMapper.adduser(username, password, nickname);
    }

    public int updatePassword(String username, String password){
        return userMapper.updatePassword(username, password);
    }
}
