package com.nwafu.seckill.controller;


import com.nwafu.seckill.entity.Goods;
import com.nwafu.seckill.entity.Comment;
import com.nwafu.seckill.entity.SeckillOrder;
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

    @GetMapping("comment")
    public String findUserComment(Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("session_user");
        List<Comment> comments = userService.findUserComment(user.getUserId());
        model.addAttribute("comments", comments);
        return "page/userinfor_comment";
    }

    @GetMapping("collect")
    public String findUserCollect(Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("session_user");
        List<Goods> goods = userService.findUserCollect(user.getUserId());
        model.addAttribute("goods", goods);
        return "page/userinfor_collection";
    }

    @GetMapping("order")
    public String findUserOrder(Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("session_user");
        List<SeckillOrder> seckillOrders = userService.findUserOrder(user.getUserId());
        System.out.println(seckillOrders);
        model.addAttribute("seckillOrders", seckillOrders);
        return "page/userinfor_order";
    }


}
