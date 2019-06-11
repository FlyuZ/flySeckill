package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User userlogin(@Param("username") String username, @Param("password") String password);

    @Insert("INSERT INTO user (username,password,nickname,mail) VALUES (#{username},#{password}, #{nickname}, #{email})")
    int adduser(@Param("username") String username, @Param("password") String password,
                @Param("nickname") String nickname, @Param("email") String mail);
}
