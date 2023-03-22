package com.example.forumBackEnd.pojo.enumClass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.stream.Stream;

public enum TagStatus implements ValueEnum{
    // Jackson2.6及以上版本支持
    @JsonProperty("hit")
    HIT("hit"),
    @JsonProperty("normal")
    NORMAL("normal"),
    @JsonProperty("hidden")
    HIDDEN("hidden");

    private final String value;

    TagStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

//    public String toValue() {
//        switch (this) {
//            case HIT: return "hit";
//            case NORMAL: return "normal";
//            case HIDDEN: return "hidden";
//        }
//        return null;
//    }

    @JsonCreator
    public static TagStatus forValue(String value) {
        if(value == null){
            return null;
        }
        return Stream.of(TagStatus.values())
                .filter(enumValue -> enumValue.name().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }


}
