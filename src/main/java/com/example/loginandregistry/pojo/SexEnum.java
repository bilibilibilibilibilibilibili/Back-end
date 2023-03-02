package com.example.loginandregistry.pojo;

import java.io.IOException;

/**
 * 性别（生理）
 */
public enum SexEnum {
    EMPTY, PURPLE, UNKNOWN;

    public String toValue() {
        switch (this) {
            case EMPTY: return "\u7537";
            case PURPLE: return "\u672a\u77e5";
            case UNKNOWN: return "\u5973";
        }
        return null;
    }

    public static SexEnum forValue(String value) throws IOException {
        if (value.equals("\u7537")) return EMPTY;
        if (value.equals("\u672a\u77e5")) return PURPLE;
        if (value.equals("\u5973")) return UNKNOWN;
        throw new IOException("Cannot deserialize SexEnum");
    }
}
