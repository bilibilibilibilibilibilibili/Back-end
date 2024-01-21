package com.example.forumBackEnd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor

public class Fllow {
    /**
     * 关注者id
     */
    private int from;
    /**
     * 被关注者id
     */
    private int to;
}

