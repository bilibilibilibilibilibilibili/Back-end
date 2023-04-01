package com.example.forumBackEnd.mapper;

import com.example.forumBackEnd.pojo.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TagMapper {
    @Select("SELECT * FROM tag_table WHERE name=#{name}")
    @Results(id="TagMap", value={
            @Result(property = "name", column = "name"),
            @Result(property = "status", column = "status")
    })
    List<Tag> findByName(@Param("name") String name);

    @Insert("INSERT INTO tag_table VALUES(#{name}, #{status})")
    int insertTag(Tag tag);
}
