package com.nwafu.seckill.controller;

import com.nwafu.seckill.entity.Address;
import com.nwafu.seckill.entity.User;
import com.nwafu.seckill.mapper.AddressMapper;
import com.nwafu.seckill.service.AddressService;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("Address")
public class AddressController
{
    @Autowired
    private AddressService addressService;

    //进入地址详情列表
    @GetMapping("list")
    public String getAll(Model model, User user){
        List<Address> addresses = addressService.getUsersAddress(user);
        model.addAttribute("addresses",addresses);
        return "address_list";
    }

    //添加新的地址
    @PostMapping("add")
    public String add(Address address){
//        addressService.add(address);
        return "redirect:/Address/list";
    }

    //将已有地址设置为默认地址
    @PostMapping("setDefaultAddress")
    public String setDefaultAddress(Address address ){
        addressService.setDefaultAddress(address);
        return "redirect:/Address/list";
    }
    //获得要更新的地址信息
    @GetMapping("update/{id}")
    public String update(@PathVariable int userId,Model model){
        model.addAttribute("addresses",addressService.findByUserId(userId));
        return "address_update";
    }
    //更新地址信息
    @PostMapping("update")
    public String update(Address address){
        Address address1 = addressService.findByAddressId(address.getAddressId());
        address1.setAddress(address.getAddress());
        addressService.updateAddress(address1.getAddressId(),address1.getAddress());
        return "redirect:/Address/update";
    }
    //删除地址
    @PostMapping("delete/{id}")
    public String delete(@PathVariable int addressId){
        addressService.deleteAddress(addressId);
        return "redirect:/Address/list";
    }
}
