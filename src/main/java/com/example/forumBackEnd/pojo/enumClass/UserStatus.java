package com.example.forumBackEnd.pojo.enumClass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * 账号状态
 */
public enum UserStatus implements ValueEnum{
    @JsonProperty("BAN")
    BAN("BAN"),
    @JsonProperty("CANCEL")
    CANCEL("CANCEL"),
    @JsonProperty("GUEST")
    GUEST("GUEST"),
    @JsonProperty("NORMAL")
    NORMAL("NORMAL");

    private String value;

    UserStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

//    public String toValue() {
//        switch (this) {
//            case BAN: return "ban";
//            case CANCEL: return "cancel";
//            case GUEST: return "guest";
//            case NORMAL: return "normal";
//        }
//        return null;
//    }
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static UserStatus forValue(String value){
        if(value == null){
            return null;
        }
        return Stream.of(UserStatus.values())
                .filter(enumValue -> enumValue.name().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
