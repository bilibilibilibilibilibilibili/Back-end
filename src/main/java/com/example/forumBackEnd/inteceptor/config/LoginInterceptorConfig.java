package com.example.forumBackEnd.inteceptor.config;

import com.example.forumBackEnd.inteceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器配置类
 */
@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer{
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 设置拦截哪些路径，不拦截哪些路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor);
    }

//    /**
//     * 全局注入拦截器配置Bean
//     * @return
//     */
//    @Bean
//    public LoginInterceptor loginInterceptor(){
//        return new LoginInterceptor();
//    }
}
