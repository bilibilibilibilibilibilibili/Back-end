package com.example.forumBackEnd.mapper;

import com.example.forumBackEnd.mapper.typeHandler.*;
import com.example.forumBackEnd.mapper.typeHandler.enumTypeHandler.PostStatusTypeHandler;
import com.example.forumBackEnd.pojo.Post;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface PostMapper {
    @Insert("INSERT INTO post_table(author, title, content, resources, tag, length, comment_Yor, status) " +
            "VALUES(#{author}, #{title}, #{content}, #{mediaResources}, #{tag}, #{length}," +
            "#{commentYorN, jdbcType=INTEGER}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addPost(Post post);

    @Select("SELECT * FROM post_table WHERE status='pass' ORDER BY lastComment desc LIMIT #{offset},10")
    @Results(id="PostMap", value={
            @Result(property = "id", column = "id", id=true, jdbcType = JdbcType.INTEGER),
            @Result(property = "title", column = "title", jdbcType = JdbcType.VARCHAR),
            @Result(property = "content", column = "content", jdbcType = JdbcType.LONGVARCHAR),
            @Result(property = "mediaResources", column = "resources", jdbcType = JdbcType.LONGVARCHAR, typeHandler = ListObjectTypeHandler.class),
            @Result(property = "views", column = "views", jdbcType = JdbcType.INTEGER),
            @Result(property = "likes", column = "likes", jdbcType = JdbcType.INTEGER),
            @Result(property = "collections", column = "collections", jdbcType = JdbcType.INTEGER),
            @Result(property = "length", column = "length", jdbcType = JdbcType.INTEGER),
            @Result(property = "releaseTime", column = "releaseTime", jdbcType = JdbcType.TIME),
            @Result(property = "lastCommentTime", column = "lastComment", jdbcType = JdbcType.TIME),
            @Result(property = "author", column = "author", jdbcType = JdbcType.INTEGER),
            @Result(property = "status", column = "status", jdbcType = JdbcType.VARCHAR, typeHandler = PostStatusTypeHandler.class),
            @Result(property = "tag", column = "tag", jdbcType = JdbcType.LONGVARCHAR, typeHandler = ListTypeHandler.class), //many = @Many(select = "com.example.forumBackEnd.mapper.TagMapper.findByName")
            @Result(property = "comment", column = "comment", jdbcType = JdbcType.INTEGER),
            @Result(property = "commentYorN", column = "comment_Yor", jdbcType = JdbcType.BOOLEAN),
            @Result(property = "topComment", column = "topComment", jdbcType = JdbcType.INTEGER)

    })
    List<Post> selectPostByLastComment(@Param("offset") int offset);

    @Select("SELECT * FROM post_table WHERE status='pass' ORDER BY releaseTime asc LIMIT #{offset},10")
    @ResultMap(value = "PostMap")
    List<Post> selectPostByTime(@Param("offset") int offset);

    @Select("SELECT * FROM post_table WHERE id=#{id}")
    @ResultMap(value = "PostMap")
    Post selectPostById(@Param("id") int id);

    @Update("UPDATE post_table SET comment=comment+1, lastComment=CURRENT_TIMESTAMP WHERE id=#{postId}")
    int updatePostOnNewComment(@Param("postId") int postId);

    @Select("SELECT comment FROM post_table WHERE id=#{postId}")
    int selectCommentFloorByPostId(@Param("postId") int postId);

//    @Insert("INSERT INTO post_table(author, title, content, resources, tag, length, comment_Yor, status) " +
//            "VALUES(#{author}, #{title}, #{content}, #{mediaResources}, #{tag}, #{length}," +
//            "#{commentYorN, jdbcType=INTEGER}, #{status})" +
//            "INSERT INTO post_table(tag) VALUES (42LLB2MXXM======)")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    int addNews(Post news);

    @Select("SELECT * FROM post_table WHERE tag LIKE '%42LLB2MXXM======%' ORDER BY id desc LIMIT #{offset}, 10")
    @ResultMap(value = "PostMap")
    List<Post> selectNews(@Param("offset") int offset);
}
