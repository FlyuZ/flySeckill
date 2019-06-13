package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.Address;

import java.util.List;

public interface AddressMapper {
    /**
     * 根据用户ID，查地址表
     */
    List<Address> findByUserId();

    /**
     * 根据用户ID，查地址表
     */
    String findDefaultAddress();

    /**
     * 添加地址表
     */
    int insert();

    /**
     * 删除地址表
     */
    void delete();

    /**
     * 设置为地址状态(默认、非默认)
     */
    int update();
}