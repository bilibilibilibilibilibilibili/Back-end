package com.example.loginandregistry.pojo.enumClass;

import java.io.IOException;

/**
 * 账号身份与权限
 */
public enum Identity implements ValueEnum{
    ADMIN("admin"),
    ORGANIZATION("organization"),
    PRIOR_ADMIN("prior_admin"),
    STUDENT("student"),
    SUPER_ADMIN("super_admin"),
    TEACHER("teacher");

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
        if (value.equals("admin")) return ADMIN;
        if (value.equals("organization")) return ORGANIZATION;
        if (value.equals("priorAdmin")) return PRIOR_ADMIN;
        if (value.equals("student")) return STUDENT;
        if (value.equals("superAdmin")) return SUPER_ADMIN;
        if (value.equals("teacher")) return TEACHER;
        throw new IOException("Cannot deserialize Empty");
    }
}