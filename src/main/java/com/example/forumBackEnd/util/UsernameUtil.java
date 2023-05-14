package com.example.forumBackEnd.util;

import org.junit.Test;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class UsernameUtil {

    public static String getRandomGuestName(){
        StringBuilder name = new StringBuilder("Guest_");
        Random r = new Random();
        for(int i=0; i<11;i++){
            name.append(r.nextInt(10));
        }
        return name.toString();
    }
}
