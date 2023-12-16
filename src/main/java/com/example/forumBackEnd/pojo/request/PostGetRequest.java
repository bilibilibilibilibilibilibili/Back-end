package com.example.forumBackEnd.pojo.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PostGetRequest {
    private String token;
    private int offset;
    private int id;
    private String tag;

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }


}
