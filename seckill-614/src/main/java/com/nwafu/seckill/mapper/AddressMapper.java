package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.Address;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AddressMapper {
    /**
     * 根据用户ID，查地址表
     */
    @Select("Select * from address where user_id = #{userId}")
    List<Address> findByUserId(@Param("userId") int userId);


    /**
     * 根据地址ID，查地址表
     */
    @Select("Select address from address where address_id = #{addressId}")
    String findByAddressrId(@Param("addressId") int addressId);


    /**
     * 添加地址表
     */
    @Insert("insert into address(user_id,address) values(#{userId},#{address})")
    int insert(@Param("userId") int userId, @Param("address") String address);

    /**
     * 根据地址id删除地址表
     */
    @Delete("delete from address where address_id = #{addressId}")
    void delete(@Param("addressId") int addressId);


    /**
     * 将地址设置为默认地址
     */
    @Update("update user set address = #{address} where user_id = #{userId}")
    void setDefaultAddress(@Param("userId") int userId, @Param("address") String address);


}