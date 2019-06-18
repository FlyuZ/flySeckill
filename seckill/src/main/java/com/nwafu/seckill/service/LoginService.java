package com.nwafu.seckill.service;


import com.nwafu.seckill.entity.User;

import com.nwafu.seckill.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
