package com.example.loginandregistry.pojo;

import com.example.loginandregistry.pojo.enumClass.TagStatus;

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

    public TagStatus getStatus() { return status; }
    public void setStatus(TagStatus value) { this.status = value; }
}
