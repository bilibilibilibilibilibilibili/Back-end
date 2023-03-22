package com.example.forumBackEnd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.forumBackEnd.mapper")
@SpringBootApplication
public class ForumBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumBackEndApplication.class, args);
    }

}
