package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    //根据用户名密码用户登录
    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User userlogin(@Param("username") String username, @Param("password") String password);

    //注册用户
    @Insert("INSERT INTO user (username,password,nickname) VALUES (#{username},#{password}, #{nickname})")
    int adduser(@Param("username") String username, @Param("password") String password,
                @Param("nickname") String nickname);
    /**
     * 修改个人信息
     */
    @Update("UPDATE user set nickname=#{nickname},mailbox=#{mailbox} where username=#{username}")
    void update(@Param("nickname") String nickname, @Param("mailbox") String mailbox,
                @Param("username") String username);
    /**
     * 根据Id获取个人信息
     */
    @Select("Select * from user where userId=#{userId}")
    User getById(Integer userId);
    /**
     * 获取所有
     */
    @Select("Select * from user")
    List<User> getUsers();
}