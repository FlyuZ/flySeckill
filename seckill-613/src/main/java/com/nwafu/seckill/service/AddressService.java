package com.nwafu.seckill.service;

import com.nwafu.seckill.entity.Address;
import com.nwafu.seckill.entity.User;
import com.nwafu.seckill.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 根据user_id来获得该用户的所有地址
     */
    public List<String[]> findByUserId(int userId) {
        List<Address> addressesList = addressMapper.findByUserId(userId);
        List<String[]> res = new ArrayList<>();
        for(Address address : addressesList){
            res.add((address.getAddress()+ "," + address.getAddressId()).split(","));
        }
        return res;
    }

    /**
     * 添加地址表
     */
    public void add(int userId, String addressText) {
        addressMapper.insert(userId, addressText);
    }

    /**
     * 将地址设置为默认地址
     */
    public void setDefaultAddress(int userId, String addressText) {
        addressMapper.setDefaultAddress(userId, addressText);
    }

    /**
     * 根据id删除地址表
     */
    public void deleteAddress(int addressId) {
        addressMapper.delete(addressId);
    }

}
