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
    /**
     * 评论人的id
     */
    private int fromId;
    /**
     * 评论的评论人id*/
    private int toId;
    /**
     * 参考资源*/
    private int reference;
    @JsonCreator
    public Comment(@JsonProperty("postId") int postId, @JsonProperty("floor") int floor,
                   @JsonProperty("content") String content, @JsonProperty("time") String time,
                   @JsonProperty("likes") int likes, @JsonProperty("status") CommentStatus status,
                   @JsonProperty("ip") String ip, @JsonProperty("userId") int userId,
                   @JsonProperty("fromId") int fromId, @JsonProperty("toId") int toId,
                   @JsonProperty("reference") int reference) {
        this.postId = postId;
        this.floor = floor;
        this.content = content;
        this.time = time;
        this.likes = likes;
        this.status = status;
        this.ip = ip;
        this.userId = userId;
        this.fromId = fromId;
        this.toId = toId;
        this.reference =reference;
    }
}
