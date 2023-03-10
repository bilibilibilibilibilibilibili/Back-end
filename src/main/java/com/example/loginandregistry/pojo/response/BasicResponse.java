package com.example.loginandregistry.pojo.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BasicResponse {
    private int code=200;
    private String message;
    private String data;
}
