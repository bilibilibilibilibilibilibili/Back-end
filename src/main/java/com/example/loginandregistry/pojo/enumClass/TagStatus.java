package com.example.loginandregistry.pojo.enumClass;

import java.io.IOException;

public enum TagStatus {
    HIT, NORMAL, HIDDEN;

    public String toValue() {
        switch (this) {
            case HIT: return "hit";
            case NORMAL: return "normal";
            case HIDDEN: return "hidden";
        }
        return null;
    }

    public static TagStatus forValue(String value) throws IOException {
        if (value.equals("hit")) return HIT;
        if (value.equals("normal")) return NORMAL;
        if(value.equals("hidden")) return HIDDEN;
        throw new IOException("Cannot deserialize TagStatus");
    }
}
