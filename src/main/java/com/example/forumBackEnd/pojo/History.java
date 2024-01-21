package com.example.forumBackEnd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class History {
    /**
     * 用户id
     */
    private int userId;
    /**
     * 帖子id
     */
    private int postId;
    /**
     * 浏览时间
     */
    private String time;
}
