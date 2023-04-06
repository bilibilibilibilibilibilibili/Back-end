package com.example.forumBackEnd.mapper;

import com.example.forumBackEnd.mapper.typeHandler.enumTypeHandler.CommentStatusTypeHandler;
import com.example.forumBackEnd.pojo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommentMapper {
    @Insert("INSERT INTO comment_table(user_id, floor, content, reference_c, post_id, comment_ip)" +
            "VALUES(#{userId}, #{floor}, #{content}, #{reference}, #{postId}, #{ip})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addComment(Comment comment);

    @Select("SELECT * FROM comment_table WHERE post_id=#{id} AND comment_sta='ok' ORDER BY floor LIMIT #{offset},10")
    @Results(id="CommentMap", value={
            @Result(property = "id", column = "comment_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "floor", column = "floor"),
            @Result(property = "content", column = "content"),
            @Result(property = "time", column = "time"),
            @Result(property = "reference", column = "reference_c"),
            @Result(property = "likes", column = "comment_lik"),
            @Result(property = "status", column = "comment_sta", typeHandler = CommentStatusTypeHandler.class),
            @Result(property = "ip", column = "comment_ip")
    })
    List<Comment> selectCommentByPost(@Param("id") int id, @Param("offset") int offset);

    @Select("SELECT * FROM comment_table WHERE comment_id=#{id} AND comment_sta='ok'")
    @ResultMap(value="CommentMap")
    List<Comment> selectCommentById(@Param("id") int id);
}
