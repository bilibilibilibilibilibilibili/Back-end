package com.example.forumBackEnd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {
    /*
    实现页面跳转
     */
    @GetMapping("login")
    public String login(){
        return "login";
    }
    /*
    注册页面跳转
     */
    @GetMapping("registry")
    public String registry(){
        return "registry";
    }

}
