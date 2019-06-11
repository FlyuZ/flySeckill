package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.seckillOrder;
import com.nwafu.seckill.entity1.SeckillOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface seckillOrderMapper {

    /**
     * 插入购买订单明细
     * @return 返回该SQL更新的记录数，如果>=1则更新成功
     */
    int insertOrder();

    /**
     * 根据秒杀用户ID查询该用户的所有订单明细数据
     */
    List<SeckillOrder> findByUserId();
}