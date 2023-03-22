package com.example.forumBackEnd.pojo.enumClass;

import java.io.IOException;
import java.util.stream.Stream;

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
        if(value == null){
            return null;
        }
        return Stream.of(SexEnum.values())
                .filter(enumValue -> enumValue.name().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
