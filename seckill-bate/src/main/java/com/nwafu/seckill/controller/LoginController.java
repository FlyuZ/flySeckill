package com.nwafu.seckill.controller;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.nwafu.seckill.entity.User;
import com.nwafu.seckill.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/seckill")
public class LoginController {

    @Autowired
    private LoginService userLoginService;


    @RequestMapping(value = {"/loginpage"})
    public String LoginPage() {
        return "page/login";
    }

    /**
     * 获取用户名与密码，用户登录
     *
     * @return 登录成功页面
     */
    @PostMapping(value = {"/login"})
    @ResponseBody
    public String userLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password, HttpServletRequest request) {
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userLoginService.userLogin(username, md5Password);
        if (user != null) {  //登录成功
            System.out.println(user.toString());
            request.getSession().setAttribute("session_user", user);     //将用户信息放入session
            //request.getSession().setAttribute("user", user.getNickname());
            return "/seckill/list"; //返回首页
        }
        return "error";
    }

    @RequestMapping(value = {"/registerpage"})
    public String registerpage() {
        return "page/register";
    }

    @RequestMapping(value = {"/updatepwdpage"})
    public String updatepwdpage() {
        return "page/find_pwd";
    }

    /**
     * 注册新用户
     *
     * @return 注册结果
     */
    @PostMapping(value = {"/register"})
    @ResponseBody
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("nickname") String nickname) {
        System.out.println(username + password + nickname);
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        int res = userLoginService.adduser(username, md5Password, nickname);
        if (res == 0) {
            return "error";
        } else {
            return "success";
        }
    }


    /**
     * 判断验证码及更新数据库
     */
    @PostMapping(value = {"/updatePassword"})
    @ResponseBody
    public String updatePassword(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         HttpServletRequest request) {

            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            int res = userLoginService.updatePassword(username, md5Password);
            if (res == 1)
                return "success";
            else
                return "failed";

    }
}
