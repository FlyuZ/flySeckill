package com.nwafu.seckill.controller;

import com.nwafu.seckill.entity.AddressTemp;
import com.nwafu.seckill.entity.User;
import com.nwafu.seckill.service.AddressService;
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
    @GetMapping("/user_center")
    public String getAll(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("session_user");
        if(user != null) {
            List<AddressTemp> addresses = addressService.findByUserId(user.getUserId());
            model.addAttribute("addressList", addresses);
        }
            return "/page/userinfor_";
    }

    //添加新的地址
    @PostMapping("address/add")
    @ResponseBody
    public String add(@RequestParam("address") String addressText,
                      @RequestParam("receive_name") String name,
                      @RequestParam("telephone") String phone,
                      HttpServletRequest request){
        String address = addressText + ',' + phone + "," + name;
        User user = (User) request.getSession().getAttribute("session_user");
        addressService.add(user.getUserId(), address);
        return "success";
    }

    //将已有地址设置为默认地址
    @PostMapping("address/setDefaultAddress")
    @ResponseBody
    public String setDefaultAddress(@RequestParam("address_id") String address_id,
                                    HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("session_user");
        String addressText = addressService.findByAddressId(Integer.valueOf(address_id));
        addressService.setDefaultAddress(user.getUserId(), addressText);
        user.setAddress(addressText);
        request.getSession().setAttribute("session_user", user);
        return "success";
    }

    //删除地址
    @PostMapping("address/delete")
    @ResponseBody
    public String delete(@RequestParam("addressId") int addressId){
        addressService.deleteAddress(addressId);
        return "success";
    }
}
