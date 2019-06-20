package com.nwafu.seckill.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nwafu.seckill.entity.Admin;
import com.nwafu.seckill.entity.Goods;
import com.nwafu.seckill.mapper.AdminMapper;
import com.nwafu.seckill.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    //管理员登录
    public Admin adminLogin(String admin_name, String admin_password) {
        return adminMapper.adminlogin(admin_name,admin_password);
    }



    }




