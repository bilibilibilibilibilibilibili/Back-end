package com.example.forumBackEnd.mapper;

import com.example.forumBackEnd.mapper.typeHandler.ListObjectTypeHandler;
import com.example.forumBackEnd.mapper.typeHandler.ListTypeHandler;
import com.example.forumBackEnd.mapper.typeHandler.enumTypeHandler.PostStatusTypeHandler;
import com.example.forumBackEnd.pojo.Post;
import com.example.forumBackEnd.pojo.User;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface SearchMapper {
    @Select("SELECT userName FROM user_table WHERE userName LIKE CONCAT('%',#{userName},'%')")
    @Results(id="NameMap", value={
            @Result(property = "userName", column = "userName", jdbcType = JdbcType.VARCHAR)
    })
    List<User> searchByName(@Param("userName") String userName);

    @Select("SELECT * FROM post_table WHERE tag LIKE CONCAT('%',#{tag},'%')")
    @Results(id="TagMap", value={
            @Result(property = "tag", column = "tag", jdbcType = JdbcType.LONGVARCHAR, typeHandler = ListTypeHandler.class)
    })
    List<Post> searchByTag(@Param("tag") String Tag);

    @Select("SELECT * FROM post_table WHERE title LIKE CONCAT('%',#{title},'%')")
    @Results(id="TitleMap", value={
            @Result(property = "title", column = "title", jdbcType = JdbcType.VARCHAR, typeHandler = ListTypeHandler.class)
    })
    List<Post> searchByTitle(@Param("title") String Title);
}
