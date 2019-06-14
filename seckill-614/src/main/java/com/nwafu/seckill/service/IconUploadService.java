package com.nwafu.seckill.service;

import com.nwafu.seckill.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class IconUploadService {
    @Autowired
    private UserMapper userMapper;

    @Value("${web.upload-path}")
    private String path;

    public String iconUpload(MultipartFile file){
        //创建文件在服务器端的存放位置
        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + fileSuffix;
        File files = new File( path + "/" + fileName);
        //上传
        if (!files.getParentFile().exists()) {
            files.getParentFile().mkdir();
        }
        try {
            file.transferTo(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "../" + fileName;
    }

    public  void addUserIcon(String fileName, int userId){
        userMapper.addIcon(fileName, userId);
    }
}
