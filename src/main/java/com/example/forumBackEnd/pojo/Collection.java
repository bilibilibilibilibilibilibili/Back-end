package com.example.forumBackEnd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Collection {
    /**
     * 用户id
     */
    private int userId;
    /**
     * 帖子id
     */
    private int postId;
    /**
     * 收藏时间
     */
    private String time;
    /**
     * 类型
     */
    private String category;

}
