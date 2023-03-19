package com.example.loginandregistry.pojo.enumClass;

import java.io.IOException;

public enum PostStatus implements ValueEnum{
    PASS("pass"),
    RETURN("return"),
    WAIT("wait");

    private String value;

    PostStatus(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static PostStatus forValue(String value) throws IOException {
        if (value.equals("pass")) return PASS;
        if (value.equals("return")) return RETURN;
        if (value.equals("wait")) return WAIT;
        throw new IOException("Cannot deserialize PostStatus");
    }
}
