package com.example.loginandregistry.pojo;

import java.io.IOException;

public enum PostStatus {
    PASS, RETURN, WAIT;

    public String toValue() {
        switch (this) {
            case PASS:
                return "pass";
            case RETURN:
                return "return";
            case WAIT:
                return "wait";
        }
        return null;
    }

    public static PostStatus forValue(String value) throws IOException {
        if (value.equals("pass")) return PASS;
        if (value.equals("return")) return RETURN;
        if (value.equals("wait")) return WAIT;
        throw new IOException("Cannot deserialize PostStatus");
    }
}
