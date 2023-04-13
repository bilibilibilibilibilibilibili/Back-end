package com.example.forumBackEnd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaResource {
    /**
     * 文件名
     */
    private String name;
    /**
     * 路径
     */
    private String path;
}
