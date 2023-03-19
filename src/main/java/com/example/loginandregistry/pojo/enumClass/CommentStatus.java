package com.example.loginandregistry.pojo.enumClass;

import java.io.IOException;

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
        if(value.equals("visible")) return VISIBLE;
        if(value.equals("invisible")) return INVISIBLE;
        if(value.equals("wait")) return WAIT;
        throw new IOException("Cannot deserialize CommentStatus");
    }
}
