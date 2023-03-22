package com.example.forumBackEnd.pojo;

/**
 * tag
 */
public class TagTest {
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
    private String status;

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }
}
