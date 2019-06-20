package com.nwafu.seckill.mapper;

import com.github.pagehelper.Page;
import com.nwafu.seckill.entity.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface GoodsMapper {
    /**
     * 查询所有商品的信息
     */
    @Select("SELECT * FROM goods")
    Page<Goods> findAll();


    @Delete("DELETE FROM goods WHERE goods_id = #{goodsid}")
    Integer deleteById(@Param("goodsid") int goodsid);

    @Insert("INSERT INTO goods(category_id,goods_no,goods_name,goods_describe,original_price,current_price,"+
            "image,stock_count,start_time,end_time) "+
            "VALUES(#{categoryid},#{goodsno},#{goodsname},#{goodsdescribe}," +
            "#{originalprice},#{currentprice},#{image},#{stockcount},#{starttime},#{endtime})")
    void insert(@Param("categoryid") int categoryid,
                   @Param("goodsno") String goodsno,@Param("goodsname") String goodsname,
                   @Param("goodsdescribe") String goodsdescribe,@Param("originalprice") double originalprice,
                   @Param("currentprice") double currentprice,@Param("image") String image,
                   @Param("stockcount") int stockcount,@Param("starttime") String starttime,
                   @Param("endtime") String endtime);

    /**
     * 模糊查询 含某字的商品
     *
     * @return 返回所有含某字段的商品
     */
    @Select("SELECT * FROM goods WHERE goods_name LIKE '%${goodsName}%'")
    Page<Goods> findByName(@Param("goodsName") String goodsName);


    /**
     * 模糊查询 在某时间段内开始的商品
     *
     * @return 返回所有在时间段内开始的商品
     */
    @Select("SELECT * FROM goods WHERE start_time BETWEEN #{startTime1} AND #{startTime2}")
    Page<Goods> findByTime(@Param("startTime1") Date startTime1, @Param("startTime2") Date startTime2);




}