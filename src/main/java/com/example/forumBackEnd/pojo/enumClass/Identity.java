package com.example.forumBackEnd.pojo.enumClass;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * 账号身份与权限
 */
public enum Identity implements ValueEnum{
    @JsonProperty("ADMIN")
    ADMIN("ADMIN"),
    @JsonProperty("ORGANIZATION")
    ORGANIZATION("ORGANIZATION"),
    @JsonProperty("PRIOR_ADMIN")
    PRIOR_ADMIN("PRIOR_ADMIN"),
    @JsonProperty("STUDENT")
    STUDENT("STUDENT"),
    @JsonProperty("SUPER_ADMIN")
    SUPER_ADMIN("SUPER_ADMIN"),
    @JsonProperty("TEACHER")
    TEACHER("TEACHER");

    private String value;

    Identity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    //    public String toValue() {
//        switch (this) {
//            case ADMIN: return "admin";
//            case ORGANIZATION: return "organization";
//            case PRIOR_ADMIN: return "priorAdmin";
//            case STUDENT: return "student";
//            case SUPER_ADMIN: return "superAdmin";
//            case TEACHER: return "teacher";
//        }
//        return null;
//    }

    public static Identity forValue(String value) throws IOException {
        if(value == null){
            return null;
        }
        return Stream.of(Identity.values())
                .filter(enumValue -> enumValue.name().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}