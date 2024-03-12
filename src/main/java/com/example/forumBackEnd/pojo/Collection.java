package com.example.forumBackEnd.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
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

    @JsonCreator
    public Collection(@JsonProperty("userId") int userId, @JsonProperty("postId") int postId,
                      @JsonProperty("time") String time, @JsonProperty("category") String category){
        this.userId = userId;
        this.postId = postId;
        this.time = time;
        this.category = category;
    }

}
