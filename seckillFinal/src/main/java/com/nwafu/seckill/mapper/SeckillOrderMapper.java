package com.nwafu.seckill.mapper;


import com.nwafu.seckill.entity.SeckillOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface SeckillOrderMapper {

    /**
     * 插入购买订单明细
     *
     * @return 返回该SQL更新的记录数，如果>=1则更新成功
     */
    @Insert("INSERT ignore INTO seckill_order(user_id, goods_id, order_no, state, create_time, pay_time, address, price) " +
            "VALUES (#{userId}, #{goodsId}, #{orderNo}, #{state}, #{createTime}, #{payTime}, #{address}, #{price})")
    int insertOrder(@Param("userId") int userId, @Param("goodsId") int goodsId,
                    @Param("orderNo") String orderNo, @Param("state") String state,
                    @Param("createTime") Date createTime, @Param("payTime") Date payTime,
                    @Param("address") String address, @Param("price") Double price);

    /**
     * 根据秒杀用户ID查询该用户的所有订单明细数据
     */
    @Select("SELECT user_id, goods_id, order_no, state, create_time, pay_time, address, price FROM seckill_order where user_id = #{userId}")
    List<SeckillOrder> findByUserId(@Param("userId") int userId);


    @Select("select count(*) from seckill_order where user_id = #{userId} AND goods_id = #{goodsId}")
    int findExist(@Param("userId") int userId, @Param("goodsId") int goodsId);

    /**
     * 更改支付状态
     */
    @Update("UPDATE seckill_order SET state = #{state} WHERE order_no = #{orderNo}")
    int updateState(@Param("state") String state, @Param("orderNo") String orderNo);
}