package com.example.forumBackEnd.pojo.enumClass;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * 账号状态
 */
public enum UserStatus implements ValueEnum{
    BAN("ban"),
    CANCEL("cancel"),
    GUEST("guest"),
    NORMAL("normal");

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

    public static UserStatus forValue(String value) throws IOException {
        if(value == null){
            return null;
        }
        return Stream.of(UserStatus.values())
                .filter(enumValue -> enumValue.name().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
