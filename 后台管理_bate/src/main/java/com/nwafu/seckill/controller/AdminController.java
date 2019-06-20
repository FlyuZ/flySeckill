package com.nwafu.seckill.controller;

import com.nwafu.seckill.entity.Admin;

import com.nwafu.seckill.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = {""})
    public String admin() {
        return "redirect:/admin/loginpage";
    }

    @PostMapping("/login")
    @ResponseBody
    public String adminLogin(@RequestParam("admin_name") String admin_name,
                             @RequestParam("admin_password") String admin_password,
                             HttpServletRequest request){
        String md5Password = DigestUtils.md5DigestAsHex(admin_password.getBytes());
        System.out.println(md5Password);
        Admin admin = adminService.adminLogin(admin_name,md5Password);
        if(admin != null)
        {
            request.getSession().setAttribute("session_admin",admin);
            return "success";
        }
        else
            return "error";
    }
    @RequestMapping(value = {"/loginpage"})
    public String LoginPage() {
        return "page/adminLogin";
    }


}
