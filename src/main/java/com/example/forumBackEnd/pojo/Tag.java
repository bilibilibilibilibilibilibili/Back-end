package com.example.forumBackEnd.pojo;

import com.example.forumBackEnd.pojo.enumClass.TagStatus;

/**
 * tag
 */
public class Tag {
    /**
     * tag名称
     */
    private String name;
    /**
     * Tag状态
     * NORMAL -> 一般tag
     * HIT -> 热门tag
     * HIDDEN -> 隐藏
     */
    private TagStatus status;

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getStatus() { return status.getValue(); }
    public void setStatus(String value) { this.status = TagStatus.forValue(value); }
}
