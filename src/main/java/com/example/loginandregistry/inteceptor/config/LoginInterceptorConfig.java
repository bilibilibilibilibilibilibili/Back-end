package com.example.loginandregistry.inteceptor.config;

import com.example.loginandregistry.inteceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 */
@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {

    /**
     * 设置拦截哪些路径，不拦截哪些路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor())
                //添加拦截路径
                .addPathPatterns("/**")
                //添加白名单路径
                .excludePathPatterns("/swagger-resources/**");
    }

    /**
     * 全局注入拦截器配置Bean
     * @return
     */
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }
}
