package com.example.loginandregistry.pojo;

import com.example.loginandregistry.pojo.enumClass.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String author;
    /**
     * 收藏量
     */
    private String collections;
    /**
     * 评论表id
     */
    private String comment;
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
    private String id;
    /**
     * 最近评论时间
     */
    private String lastCommentTime;
    /**
     * 点赞量
     */
    private String likes;
    /**
     * 发布时间
     */
    private String releaseTime;
    /**
     * 媒体资源地址列表
     */
    private Resource[] resources;
    /**
     * 帖子发布，审核状态
     * PASS -> 通过
     * RETURN -> 退回
     * WAIT -> 等待审核
     */
    private PostStatus status;
    /**
     * Tag数组
     */
    private Tag[] tag;
    /**
     * 标题
     */
    private String title;
    /**
     * 置顶评论楼层
     */
    private String topComment;
    /**
     * 浏览量
     */
    private long views;
}



