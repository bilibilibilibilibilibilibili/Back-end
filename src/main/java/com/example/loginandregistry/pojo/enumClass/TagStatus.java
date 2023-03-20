package com.example.loginandregistry.pojo.enumClass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum TagStatus {
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
        for(TagStatus tagStatus: TagStatus.values()){
            if(tagStatus.getValue().equals(value)){
                return tagStatus;
            }
        }
        return null;
    }


}
