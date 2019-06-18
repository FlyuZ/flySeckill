
package com.nwafu.seckill.controller;

import com.nwafu.seckill.entity.User;
import com.nwafu.seckill.service.IconUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("seckill")
public class UploadController {
    @Autowired
    private IconUploadService iconUploadService;

    @PostMapping("/upload")
    @ResponseBody
    public void uploadFile(@RequestParam("fileName") MultipartFile file, HttpSession session) {
        //创建文件在服务器端的存放位置
        if(file == null || file.isEmpty()){
            return;
        }
        String filePath = iconUploadService.iconUpload(file);
        User user = (User) session.getAttribute("session_user");
        user.setIcon(filePath);
        iconUploadService.addUserIcon(filePath, user.getUserId());
        session.setAttribute("session_user", user);
    }
}