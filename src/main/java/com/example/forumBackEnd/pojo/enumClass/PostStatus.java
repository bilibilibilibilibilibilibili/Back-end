package com.example.forumBackEnd.pojo.enumClass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.stream.Stream;

public enum PostStatus implements ValueEnum{
    @JsonProperty("pass")
    PASS("pass"),
    @JsonProperty("return")
    RETURN("return"),
    @JsonProperty("wait")
    WAIT("wait");

    private String value;

    PostStatus(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static PostStatus forValue(String value) throws IOException {
//        if (value == null) return null;
//        if (value.equals("pass")) return PASS;
//        if (value.equals("return")) return RETURN;
//        if (value.equals("wait")) return WAIT;
//        else {
//            throw new ("Cannot deserialize PostStatus because there is no value!");
//        }
        if(value == null){
            return null;
        }
        return Stream.of(PostStatus.values())
                .filter(enumValue -> enumValue.name().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
