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
    List<Address> findByUserId(@Param("userId")int userId);

    /**
     * 根据地址ID，查地址表
     */
    @Select("Select address form user where address_id = #{addressId}")
    Address findByAddressId(@Param("addressId") int addressId);

    /**
     * 添加地址表
     */
    @Insert("insert into address(user_id,address) values(#{userId},#{address})")
    int insert(@Param("userId") int userId,@Param("address") String address);
    /**
     * 根据地址id删除地址表
     */
    @Delete("delete form address where address_id = #{addressId}")
    void delete(@Param("addressId") int addressId);
    /**
     * 设置为地址状态(默认、非默认)
     */
//    @Update("update address set address_state = #{addressState} where address_id = #{addressId}")
//    int update(@Param("addressState") String address_state,@Param("addressId") int addressId);
    /**
     * 将地址设置为默认地址
     */
    @Update("update user set address = #{address} where user_id = #{userId}")
     void setDefaultAddress(@Param("address") String address,@Param("userId") int userId);
    /**
     * 更新地址信息
     */
    @Update("update address set address = #{address} where address_id = #{addressId}")
    void updateAddress(@Param("address") String address,@Param("addressId") int addressId);
}