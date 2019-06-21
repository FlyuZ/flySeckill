package com.nwafu.seckill.service;

import com.nwafu.seckill.entity.Goods;
import com.nwafu.seckill.mapper.CategoryMapper;
import com.nwafu.seckill.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JobSchedule {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0 30 2 * * ?")  //每天凌晨两点30分执行
    public void printDate(){
        System.out.println(new Date().toString());
        redisTemplate.delete("goods");
        List<Goods> goodsList = goodsMapper.findAll();
        for (Goods goods : goodsList) {
            //将秒杀列表数据依次放入redis缓存中，key:秒杀表的ID值；value:秒杀商品数据
            redisTemplate.boundHashOps("goods").put(goods.getGoodsId(), goods);
        }
    }
}
