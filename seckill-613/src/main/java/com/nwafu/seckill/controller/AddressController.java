package com.nwafu.seckill.controller;

import com.nwafu.seckill.entity.Address;
import com.nwafu.seckill.entity.User;
import com.nwafu.seckill.mapper.AddressMapper;
import com.nwafu.seckill.service.AddressService;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("seckill")
public class AddressController
{
    @Autowired
    private AddressService addressService;

    //进入地址详情列表
    @GetMapping("userpage/address")
    public String getAll(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("session_user");
        List<String[]> addresses = addressService.findByUserId(user.getUserId());
        model.addAttribute("addresseList",addresses);
        return "/page/address_list";
    }

    //添加新的地址
    @PostMapping("userpage/add")
    public String add(@RequestParam("addressText") String addressText,
                      @RequestParam("name") String name,
                      @RequestParam("phone") String phone,
                      HttpServletRequest request){
        String address = addressText + ',' + name + "," + phone;
        User user = (User) request.getSession().getAttribute("session_user");
        addressService.add(user.getUserId(), address);
        return "redirect:/userpage/address";
    }

    //将已有地址设置为默认地址
    @PostMapping("userpage/setDefaultAddress")
    public String setDefaultAddress(@RequestParam("addressText") String addressText,
                                    @RequestParam("name") String name,
                                    @RequestParam("phone") String phone,
                                    HttpServletRequest request){
        String address = addressText + ',' + name + "," + phone;
        User user = (User) request.getSession().getAttribute("session_user");
        addressService.setDefaultAddress(user.getUserId(), address);
        return "redirect:/userpage/address";
    }

    //删除地址
    @GetMapping("userpage/delete")
    public String delete(@RequestParam("addressId") int addressId){
        addressService.deleteAddress(addressId);
        return "redirect:/userpage/address";
    }
}
