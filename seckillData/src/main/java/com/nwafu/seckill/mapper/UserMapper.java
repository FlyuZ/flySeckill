package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    //根据用户名密码用户登录
    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User userlogin(@Param("username") String username, @Param("password") String password);

    //注册用户
    @Insert("INSERT INTO user (username,password,nickname,mail,icon) VALUES (#{username},#{password}, #{nickname}, #{email}, #{icon)")
    int adduser(@Param("username") String username, @Param("password") String password,
                @Param("nickname") String nickname, @Param("email") String mail,
                @Param("icon") String icon);
    /**
     * 修改个人信息
     */
    int update();
}