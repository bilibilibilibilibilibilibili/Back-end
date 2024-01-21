package com.example.forumBackEnd.pojo;

import com.example.forumBackEnd.pojo.enumClass.PostStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Post
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    /**
     * 作者id
     */
    private int author;
    /**
     * 收藏量
     */
    private int collections;
    /**
     * 评论数
     */
    private int comment;
    /**
     * 是否允许评论
     */
    private boolean commentYorN;
    /**
     * 文字内容（html）
     */
    private String content;
    /**
     * 帖子id
     */
    private int id;
    /**
     * 最近评论时间
     */
    private String lastCommentTime;
    /**
     * 点赞量
     */
    private int likes;
    /**
     * 发布时间
     */
    private String releaseTime;
    /**
     * 媒体资源地址列表
     */
    private List<MediaResource> mediaResources;
    /**
     * 帖子发布，审核状态
     * PASS -> 通过
     * RETURN -> 退回
     * WAIT -> 等待审核
     */
    private PostStatus status;
    /**
     * Tag名字数组
     */
    private List<String> tag;
    /**
     * 标题
     */
    private String title;
    /**
     * 置顶评论楼层
     */
    private int topComment;
    /**
     * 浏览量
     */
    private int views;
    /**
     * 文章长度
     */
    private int length;
    /**
     * 公开
     */
    private boolean isConfidential;

    @JsonCreator
    public Post(@JsonProperty("author") int author, @JsonProperty("commentYorN") boolean commentYorN,
                @JsonProperty("content") String content, @JsonProperty("resources") List<MediaResource> mediaResources,
                @JsonProperty("tag") List<String> tag, @JsonProperty("title") String title){
        this.author = author;
        this.commentYorN = commentYorN;
        this.content = content;
        this.mediaResources = mediaResources;
        this.tag = tag;
        this.title = title;
    }
}


