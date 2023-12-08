package com.example.forumBackEnd.inteceptor;

import java.lang.annotation.*;

/**
 * 自定义验证token注解
 * 将该注解加到需要验证token的controller或方法上即可
 * @desc 已登录权限验证注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginAuth {

}