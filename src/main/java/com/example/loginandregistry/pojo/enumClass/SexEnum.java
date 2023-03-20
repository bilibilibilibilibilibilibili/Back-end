package com.example.loginandregistry.pojo.enumClass;

import java.io.IOException;

/**
 * 性别（生理）
 */
public enum SexEnum implements ValueEnum{
    MALE("male"),
    FEMALE("female"),
    UNKNOWN("unknown");

    private String value;

    SexEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    //    public String toValue() {
//        switch (this) {
//            case MALE: return "male";
//            case FEMALE: return "female";
//            case UNKNOWN: return "unknown";
//        }
//        return null;
//    }

    public static SexEnum forValue(String value) throws IOException {
        if (value.equals("male")) return MALE;
        if (value.equals("female")) return FEMALE;
        if (value.equals("unknown")) return UNKNOWN;
        throw new IOException("Cannot deserialize SexEnum");
    }
}
