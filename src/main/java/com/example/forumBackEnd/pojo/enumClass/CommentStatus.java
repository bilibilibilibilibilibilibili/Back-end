package com.example.forumBackEnd.pojo.enumClass;

import java.io.IOException;
import java.util.stream.Stream;

public enum CommentStatus implements ValueEnum{
    VISIBLE("visible"),
    INVISIBLE("invisible"),
    WAIT("wait");

    private String value;

    CommentStatus(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    //    public String toValue(){
//        switch (this){
//            case VISIBLE: return  "visible";
//            case INVISIBLE: return "invisible";
//            case WAIT: return "wait";
//        }
//        return null;
//    }

    public static CommentStatus forValue(String value)throws IOException {
        if(value == null){
            return null;
        }
        return Stream.of(CommentStatus.values())
                .filter(enumValue -> enumValue.name().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
