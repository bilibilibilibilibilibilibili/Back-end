package com.example.loginandregistry.mapper;

import com.example.loginandregistry.pojo.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostMapper {
    @Insert("INSERT INTO post(author, title, content, resources, tag, status, releaseTime, views, likes, " +
            "collections, commentYorN, comment, topComment, lastCommentTime) " +
            "VALUES(${author}, #{title}, #{content}, #{resources}, #{tag}, #{status}, #{releaseTime}," +
            " #{views}, #{likes}, #{collections}, #{commentYorN}, #{comment}, #{topComment}, #{lastCommentTime})")
    int addPost(Post post);

    @Select("SELECT author, title, content, resources, tag, releaseTime, views, likes, collections, commentYorN," +
            "comment, topComment FROM post WHERE status='pass' ORDER BY lastCommentTime LIMIT #{offset},10")
    List<Post> selectPostByLastComment(int offset);

    @Select("SELECT author, title, content, resources, tag, releaseTime, views, likes, collections, commentYorN," +
            "comment, topComment FROM post WHERE status='pass' ORDER BY releaseTime LIMIT #{offset},10")
    List<Post> selectPostByTime(int offset);

}
