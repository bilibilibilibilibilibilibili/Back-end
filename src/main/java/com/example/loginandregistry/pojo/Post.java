package com.example.loginandregistry.pojo;

import java.io.IOException;

/**
 * Post
 */
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
    private PostStatus status;
    private Tag[] tag;
    /**
     * 标题
     */
    private String title;
    /**
     * 置顶评论id
     */
    private String topComment;
    /**
     * 浏览量
     */
    private long views;

    public String getAuthor() { return author; }
    public void setAuthor(String value) { this.author = value; }

    public String getCollections() { return collections; }
    public void setCollections(String value) { this.collections = value; }

    public String getComment() { return comment; }
    public void setComment(String value) { this.comment = value; }

    public boolean getCommentYorN() { return commentYorN; }
    public void setCommentYorN(boolean value) { this.commentYorN = value; }

    public String getContent() { return content; }
    public void setContent(String value) { this.content = value; }

    public String getid() { return id; }
    public void setid(String value) { this.id = value; }

    public String getLastCommentTime() { return lastCommentTime; }
    public void setLastCommentTime(String value) { this.lastCommentTime = value; }

    public String getLikes() { return likes; }
    public void setLikes(String value) { this.likes = value; }

    public String getReleaseTime() { return releaseTime; }
    public void setReleaseTime(String value) { this.releaseTime = value; }

    public Resource[] getResources() { return resources; }
    public void setResources(Resource[] value) { this.resources = value; }

    public PostStatus getStatus() { return status; }
    public void setStatus(PostStatus value) { this.status = value; }

    public Tag[] getTag() { return tag; }
    public void setTag(Tag[] value) { this.tag = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getTopComment() { return topComment; }
    public void setTopComment(String value) { this.topComment = value; }

    public long getViews() { return views; }
    public void setViews(long value) { this.views = value; }
}



