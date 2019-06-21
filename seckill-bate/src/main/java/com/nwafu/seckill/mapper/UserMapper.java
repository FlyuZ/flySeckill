package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component
public interface UserMapper {

    //根据用户名密码用户登录
    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User userlogin(@Param("username") String username, @Param("password") String password);

    //注册用户
    @Insert("INSERT INTO user (username,password,nickname,icon) VALUES (#{username},#{password}, #{nickname}, #{icon})")
    int adduser(@Param("username") String username, @Param("password") String password,
                @Param("nickname") String nickname, @Param("icon") String icon);


    @Update("UPDATE user set nickname=#{nickname},mailbox=#{mailbox} where username=#{username}")
    void update(@Param("nickname") String nickname, @Param("mailbox") String mailbox,
                @Param("username") String username);

    /**
     * 找回密码
     */
    @Update("UPDATE user set password=#{password} where username=#{username}")
    int updatePassword(@Param("username") String username,@Param("password") String password);

    /**
     * 根据Id获取个人信息
     */
    @Select("Select * from user where username=#{username}")
    User getByName(String username);

    @Select("Select * from user where user_id=#{userId}")
    User getById(int userId);
    /**
     * 获取所有
     */
    @Select("Select * from user")
    List<User> getUsers();
    /**
     * 存储文件名
     */
    @Update("UPDATE user set icon=#{icon} where user_id = #{userId}")
    int addIcon(@Param("icon")String icon,@Param("userId") Integer userId);
}