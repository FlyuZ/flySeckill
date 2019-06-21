package com.nwafu.seckill.service;

import com.nwafu.seckill.mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectService {
    @Autowired
    private CollectMapper collectMapper;

    //插入收藏
    public int insertCollect(int goodsId, int userId) {
        return collectMapper.insertCollect(goodsId, userId);
    }

    //删除该收藏
    public void deleteCollect(int goodsId, int userId) {
        collectMapper.delete(goodsId, userId);
    }

    //查看该商品 是否被改用户收藏
    public int findExist(int goodsId, int userId) {
        return collectMapper.findExist(goodsId, userId);
    }
}
