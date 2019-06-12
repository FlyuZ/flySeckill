package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GoodsMapperTest {
    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void findAll() {
        List<Goods> all = goodsMapper.findAll();

        for (Goods seckill : all) {
            System.out.println(seckill.toString());
        }
    }
}