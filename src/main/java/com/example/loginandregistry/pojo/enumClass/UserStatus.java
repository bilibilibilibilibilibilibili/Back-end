package com.example.loginandregistry.pojo.enumClass;

import java.io.IOException;

/**
 * 账号状态
 */
public enum UserStatus {
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
        if (value.equals("ban")) return BAN;
        if (value.equals("cancel")) return CANCEL;
        if (value.equals("guest")) return GUEST;
        if (value.equals("normal")) return NORMAL;
        throw new IOException("Cannot deserialize StatusEnum");
    }
}
