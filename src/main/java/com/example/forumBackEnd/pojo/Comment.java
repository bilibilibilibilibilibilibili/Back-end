package com.example.forumBackEnd.pojo;

import com.example.forumBackEnd.pojo.enumClass.CommentStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    /**
     * 评论Id - 便于后台管理
     */
    private int id;
    /**
     * 归属帖子id
     */
    private int postId;
    /**
     * 楼层
     */
    private int floor;
    /**
     * 内容
     */
    private String content;
    /**
     * 发布时间
     */
    private String time;
    /**
     * 引用帖子
     */
    private int reference;
    /**
     * 点赞数
     */
    private int likes;
    /**
     *
     */
    private CommentStatus status;
    /**
     * 发布人ip
     */
    private String ip;
    /**
     * 评论人Id
     */
    private int userId;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public CommentStatus getStatus() {
        return status;
    }

    public void setStatus(CommentStatus status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @JsonCreator
    public Comment(@JsonProperty("postId") int postId, @JsonProperty("floor") int floor,
                   @JsonProperty("content") String content, @JsonProperty("time") String time,
                   @JsonProperty("reference") int reference, @JsonProperty("likes") int likes,
                   @JsonProperty("status") CommentStatus status, @JsonProperty("ip") String ip,
                   @JsonProperty("userId") int userId) {
        this.postId = postId;
        this.floor = floor;
        this.content = content;
        this.time = time;
        this.reference = reference;
        this.likes = likes;
        this.status = status;
        this.ip = ip;
        this.userId = userId;
    }
}
