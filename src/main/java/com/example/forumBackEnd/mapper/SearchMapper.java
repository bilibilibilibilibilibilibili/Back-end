package com.example.forumBackEnd.mapper;

import com.example.forumBackEnd.pojo.User;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface SearchMapper {
    @Select("SELECT userName FROM user_table WHERE name like %{name}% LIMIT #{offset},10")
    @Results(id="NameMap", value={
            @Result(property = "Name", column = "userName", jdbcType = JdbcType.VARCHAR)
    })
    List<User> searchByName(@Param("Name") String Name );
}
