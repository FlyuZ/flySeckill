package com.nwafu.seckill.controller;


import com.nwafu.seckill.entity.User;
import com.nwafu.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/infor")
public class UserController{

    @Autowired
    private UserService userService;

    @PostMapping("update/{username}")
    @ResponseBody
    public  String update(@PathVariable("username") String username,
                          @RequestParam("nickname") String nickname,
                          @RequestParam("mailbox") String mailbox,
                          HttpServletRequest request){
        userService.updateUser(username,nickname,mailbox);
        User user = userService.findByName(username);
        request.getSession().setAttribute("session_user", user);     //将用户信息更新session
        return "success";
    }

}
