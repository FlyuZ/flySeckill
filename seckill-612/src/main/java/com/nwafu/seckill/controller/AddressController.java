package com.nwafu.seckill.controller;

import com.nwafu.seckill.entity.Address;
import com.nwafu.seckill.entity.User;
import com.nwafu.seckill.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("Address")
public class AddressController
{
    @Autowired
    private AddressMapper addressMapper;

    @GetMapping("list")
    public String getAll(Model model, User user){
        List<Address> addresses = addressMapper.findByUserId(user.getUserId());
        model.addAttribute("addresses",addresses);
        return "address_list";
    }
    @PostMapping("add")
    public String add(Address address){
        addressMapper.insert(address.getUserId(),address.getAddress());
        return "redirect:/Address/list";
    }
    @PostMapping("setDefaultAddress")
    public String setDefaultAddress(Address address ,User user){
        addressMapper.setDefaultAddress(address.getAddress(),user.getUserId());
        return "redirect:/Address/list";
    }
}
