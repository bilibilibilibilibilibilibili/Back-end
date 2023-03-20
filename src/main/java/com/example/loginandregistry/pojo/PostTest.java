package com.example.loginandregistry.pojo;

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
public class PostTest {
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
    private String status;
    /**
     * Tag名字数组
     */
    private List<TagTest> tag;
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
    private int views;

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getCollections() {
        return collections;
    }

    public void setCollections(int collections) {
        this.collections = collections;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public boolean isCommentYorN() {
        return commentYorN;
    }

    public void setCommentYorN(boolean commentYorN) {
        this.commentYorN = commentYorN;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastCommentTime() {
        return lastCommentTime;
    }

    public void setLastCommentTime(String lastCommentTime) {
        this.lastCommentTime = lastCommentTime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public List<MediaResource> getMediaResources() {
        return mediaResources;
    }

    public void setMediaResources(List<MediaResource> mediaResources) {
        this.mediaResources = mediaResources;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TagTest> getTag() {
        return tag;
    }

    public void setTag(List<TagTest> tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopComment() {
        return topComment;
    }

    public void setTopComment(String topComment) {
        this.topComment = topComment;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}



