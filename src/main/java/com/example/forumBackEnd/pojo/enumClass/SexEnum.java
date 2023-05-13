package com.example.forumBackEnd.pojo.enumClass;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * 性别（生理）
 */
public enum SexEnum implements ValueEnum{
    @JsonProperty("MALE")
    MALE("MALE"),
    @JsonProperty("FEMALE")
    FEMALE("FEMALE"),
    @JsonProperty("UNKNOWN")
    UNKNOWN("UNKNOWN");

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
