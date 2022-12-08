package com.example.loginandregistry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解@UserLoginToken
 * 添加该注解的方法必须进行token验证，即-必须登录获取token
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    //设置默认值为true
    boolean required() default true;
}
