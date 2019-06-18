package com.nwafu.seckill.service;

import com.nwafu.seckill.entity.Address;
import com.nwafu.seckill.entity.User;
import com.nwafu.seckill.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressMapper addressMapper;
    /**
     * 根据由用户直接获得该用户的所有地址
     */
    public List<Address> getUsersAddress(User user){
        List<Address> addresses = addressMapper.findByUserId(user.getUserId());
        return addresses;
    }
    /**
     * 根据user_id来获得该用户的所有地址
     */
    public List<Address> findByUserId(int userId){
        List<Address> addresses = addressMapper.findByUserId(userId);
        return addresses;
    }
    /**
     * 添加地址表
     */
    public void add(int user_id,String a,String b,String c){
        String x=a+" "+b+" "+c ;
        addressMapper.insert(user_id,x);
    }
    /**
     * 将地址设置为默认地址
     */
    public void setDefaultAddress(Address address ) {
        addressMapper.setDefaultAddress(address.getAddress(), address.getUserId());
    }
    /**
     * 根据地址id查地址表
     */
    public Address findByAddressId(int addressId){
        Address address = addressMapper.findByAddressId(addressId);
        return  address;
    }
//    public void insertAddress(int user_id,String address){
//        addressMapper.insert(user_id,address);
//    }
    /**
     * 根据id删除地址表
     */
    public void deleteAddress(int addressId){
        addressMapper.delete(addressId);
    }
    /**
     * 更新地址信息
     */
    public void updateAddress(int addressId,String address){
        addressMapper.updateAddress(address,addressId);
    }

}
