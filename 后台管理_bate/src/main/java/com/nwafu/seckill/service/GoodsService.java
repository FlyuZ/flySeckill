package com.nwafu.seckill.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nwafu.seckill.entity.Goods;
import com.nwafu.seckill.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;


    //根据主键删除商品记录
    public Integer deleteById(int goodsid){
        return goodsMapper.deleteById(goodsid);
    }

    //插入商品
    public void insert( int categoryid, String goodsno, String goodsname, String goodsdescribe,
                          double originalprice, double currentprice, String image, int stockcount,
                          String starttime, String endtime){
         goodsMapper.insert(categoryid,goodsno,goodsname,goodsdescribe, originalprice,
                currentprice,image,stockcount,starttime,endtime);

    }

    public Page<Goods> findAll(Integer pageNum, Integer pageSize)
    {

        PageHelper.startPage(pageNum, pageSize);
        return goodsMapper.findAll();
    }

    public Page<Goods> findByName(Integer pageNum, Integer pageSize , String goodsName)
    {

        PageHelper.startPage(pageNum, pageSize);
        return goodsMapper.findByName(goodsName);
    }

    public Page<Goods> findByTime(Integer pageNum, Integer pageSize , Date startTime1 , Date startTime2)
    {

        PageHelper.startPage(pageNum, pageSize);
        return goodsMapper.findByTime(startTime1,startTime2);
    }




}
