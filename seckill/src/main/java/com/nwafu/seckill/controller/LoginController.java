package com.nwafu.seckill.controller;


import com.nwafu.seckill.entity.User;
import com.nwafu.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/seckill")
public class LoginController {

    @Autowired
    private UserService userLoginService;

    @RequestMapping(value = {"/loginpage"})
    public String LoginPage() {
        return "page/login";
    }

    /**
     * 获取用户名与密码，用户登录
     *
     * @return 登录成功页面
     */
    @RequestMapping(value = {"/login"})
    @ResponseBody
    public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userLoginService.userLogin(username, md5Password);
        if (user != null) {  //登录成功
            System.out.println(user.toString());
            request.getSession().setAttribute("session_user", user);     //将用户信息放入session
            request.getSession().setAttribute("user", user.getNickname());
            return "/seckill";
        }
        return "loginError";
    }

    @RequestMapping(value = {"/registerpage"})
    public String registerpage() {
        return "page/register";
    }

    /**
     * 注册新用户
     *
     * @return 注册结果
     */
    @ResponseBody
    @RequestMapping(value = {"/register"})
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("nickname") String nickname,
                          @RequestParam("mail") String mail) {
        System.out.println(username + password + nickname + mail);
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        int res = userLoginService.adduser(username, md5Password, nickname, mail);
        if (res == 0) {
            return "注册失败！";
        } else {
            return "注册成功！";
        }
    }
}
