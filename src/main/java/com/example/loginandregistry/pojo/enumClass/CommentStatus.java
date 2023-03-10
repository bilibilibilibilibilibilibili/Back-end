package com.example.loginandregistry.pojo.enumClass;

import java.io.IOException;

public enum CommentStatus {
    VISIBLE, INVISIBLE, WAIT;

    public String toValue(){
        switch (this){
            case VISIBLE: return  "visible";
            case INVISIBLE: return "invisible";
            case WAIT: return "wait";
        }
        return null;
    }

    public static CommentStatus forValue(String value)throws IOException {
        if(value.equals("visible")) return VISIBLE;
        if(value.equals("invisible")) return INVISIBLE;
        if(value.equals("wait")) return WAIT;
        throw new IOException("Cannot deserialize CommentStatus");
    }
}
