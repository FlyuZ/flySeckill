package com.nwafu.seckill.controller;


import com.nwafu.seckill.entity.*;
import com.nwafu.seckill.service.SeckillService;
import com.nwafu.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/infor")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SeckillService seckillService;

    @PostMapping("update/{username}")
    @ResponseBody
    public String update(@PathVariable("username") String username,
                         @RequestParam("nickname") String nickname,
                         @RequestParam("mailbox") String mailbox,
                         HttpServletRequest request) {
        userService.updateUser(username, nickname, mailbox);
        User user = userService.findByName(username);
        request.getSession().setAttribute("session_user", user);     //将用户信息更新session
        return "success";
    }

    @GetMapping("comment")
    public String findUserComment(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("session_user");
        if (user != null) {
            List<CommentTemp> comments = userService.findUserComment(user.getUserId());
            model.addAttribute("comments", comments);
        }
        return "page/userinfor_comment";
    }

    @GetMapping("collect")
    public String findUserCollect(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("session_user");
        if (user != null) {
            List<Goods> goods = userService.findUserCollect(user.getUserId());
            model.addAttribute("goods", goods);
        }
        return "page/userinfor_collection";
    }

    @GetMapping("order")
    public String findUserOrder(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("session_user");
        if (user != null) {
            List<SeckillOrder> seckillOrders = userService.findUserOrder(user.getUserId());
            List<Goods> goods = new ArrayList<>();
            for (SeckillOrder seckillOrder : seckillOrders) {
                goods.add(userService.findGoods(seckillOrder.getGoodsId()));
            }
            model.addAttribute("seckillOrders", seckillOrders);
            model.addAttribute("goods", goods);
        }
        return "page/userinfor_order";
    }

    @GetMapping("exit")
    public String exit(HttpServletRequest request) {
        request.getSession().removeAttribute("session_user");
        request.getSession().removeAttribute("msg");
        return "redirect:/seckill/list";
    }

    //插入评论
    @PostMapping("insertComment")
    @ResponseBody
    public String insertComment(@RequestParam("orderNo") String orderNo,
                                @RequestParam("goodsId") int goodsId,
                                @RequestParam("comment") String comment,
                                HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("session_user");
        int res = userService.insertComment(goodsId, user.getUserId(), comment);
        if (res == 1) {
            res = seckillService.updateState("已评论", orderNo);
            return "success";
        } else{
            return "failed";
        }
    }
}
