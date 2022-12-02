package com.example.demo1.backend_info.mapper;

import com.example.demo1.backend_info.entity.UserBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //查询，可以实现登录功能
    @Select("SELECT * FROM user WHERE name = #{name} AND password = #{password}")
    UserBean getInfo(@Param("name") String name, @Param("password") String password);

    //多个参数要加@Param修饰
    //增加，可以实现注册功能
    @Insert("insert into user(name,password)values(#{name},#{password})")
    void saveInfo(@Param("name") String name, @Param("password") String password);
}