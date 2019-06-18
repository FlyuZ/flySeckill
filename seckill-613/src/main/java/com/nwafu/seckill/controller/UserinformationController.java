package com.nwafu.seckill.controller;

import com.nwafu.seckill.entity.Address;
import com.nwafu.seckill.entity.User;
import com.nwafu.seckill.mapper.UserMapper;
import com.nwafu.seckill.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/seckill")
public class UserinformationController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/user_center")
    public String get_address_information(Model model, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("session_user");
        List<Address> addresses = addressService.findByUserId(user.getUserId());
        model.addAttribute("addresses",addresses);
        return "page/userinfor_";
    }

    @PostMapping("address/deldete")
    @ResponseBody
    public String delete(@RequestParam("address_id") String address_id){
        addressService.deleteAddress(Integer.valueOf(address_id));
        return "success";
    }
    @PostMapping("address/add")
    @ResponseBody
    public String add(@RequestParam("recive_name") String recive_name,
                      @RequestParam("address") String address,
                      @RequestParam("telephone") String telephone,
                      HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("session_user");
        addressService.add(user.getUserId(),recive_name,address,telephone);
        return "success";
    }
}
