package com.example.loginandregistry.pojo.enumClass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.IOException;

public enum TagStatus {
    HIT("hit"),
    NORMAL("normal"),
    HIDDEN("hidden");

    private String alias;

    TagStatus(String value) {
        this.alias = value;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String toValue() {
        switch (this) {
            case HIT: return "hit";
            case NORMAL: return "normal";
            case HIDDEN: return "hidden";
        }
        return null;
    }

    @JsonCreator
    public static TagStatus forValue(String value) {
        for(TagStatus tagStatus: TagStatus.values()){
            if(tagStatus.getAlias().equals(value)){
                return tagStatus;
            }
        }
        return null;
    }


}
