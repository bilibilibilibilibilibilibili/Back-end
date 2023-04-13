package com.example.forumBackEnd.pojo;

import com.example.forumBackEnd.pojo.enumClass.TagStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * tag
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
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

    public String getStatus() { return status.getValue(); }
    public void setStatus(String value) { this.status = TagStatus.forValue(value); }
}
