package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AdminMapper {
    @Select("SELECT * FROM admin WHERE admin_name = #{username} AND admin_password = #{password}")
    Admin adminlogin(@Param("username") String username, @Param("password") String password);
}