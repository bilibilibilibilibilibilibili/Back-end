package com.example.loginandregistry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.loginandregistry.mapper")
@SpringBootApplication
public class LoginAndRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginAndRegistryApplication.class, args);
    }

}
