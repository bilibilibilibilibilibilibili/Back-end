package com.example.loginandregistry.pojo;

import com.example.loginandregistry.pojo.enumClass.CommentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    /**
     * 归属帖子id
     */
    private String postId;
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
    private LocalDateTime time;
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
    private int ip;
}
