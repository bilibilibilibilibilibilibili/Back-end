package com.example.loginandregistry.pojo;

import lombok.Data;

@Data
public class Resource{
    /**
     * 文件名
     */
    private String name;
    /**
     * 路径
     */
    private String path;

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getPath() { return path; }
    public void setPath(String value) { this.path = value; }
}
