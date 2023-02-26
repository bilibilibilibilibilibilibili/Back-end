package com.example.loginandregistry.pojo;

import java.io.IOException;

public enum TagStatus {
    HIT, NORMAL;

    public String toValue() {
        switch (this) {
            case HIT: return "hit";
            case NORMAL: return "normal";
        }
        return null;
    }

    public static TagStatus forValue(String value) throws IOException {
        if (value.equals("hit")) return HIT;
        if (value.equals("normal")) return NORMAL;
        throw new IOException("Cannot deserialize TagStatus");
    }
}
