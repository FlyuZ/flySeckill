package com.nwafu.seckill.controller;

import com.nwafu.seckill.entity.User;
import com.nwafu.seckill.mapper.UserMapper;
import com.nwafu.seckill.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/infor")
public class UserController{
    @Autowired
    private UserMapper userMapper;


    @GetMapping("/all")
    public String getAll(Model model){
        List<User> users = userMapper.getUsers();
        model.addAttribute("users",users);
        return "/page";
}
    @PostMapping("update/{userId}")
    @ResponseBody
    public  String update(@PathVariable("userId") String userId,
                          @RequestParam("nickname") String nickname,
                          @RequestParam("mailbox") String mailbox){
        userMapper.update(nickname,mailbox,userId);
        return "success";
    }
//    @PostMapping("update")
//    public String update(User user){
//        User us = userMapper.getById(user.getUserId());
//        us.setNickname(user.getNickname());
//
//        return "redirect:/seckill/list";
//    }
}
