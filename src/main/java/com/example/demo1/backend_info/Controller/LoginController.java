package com.example.demo1.backend_info.Controller;

import com.example.demo1.backend_info.service.UserService;
import com.example.demo1.backend_info.entity.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
@Slf4j
@Controller
public class LoginController {

    //将Service注入Web层
    @Resource
    UserService userService;

    //实现登录
    @RequestMapping("/login")
    public String show(){
        return "login";
    }

    @RequestMapping(value = "/loginIn",method = RequestMethod.POST)
    public String login(String name,String password){
        UserBean userBean = userService.LoginIn(name, password);
        log.info("name:{}",name);
        log.info("password:{}",password);
        if(userBean!=null){
            return "success";
        }else {
            return "error";
        }
    }
    @RequestMapping("/signup")
    public String disp(){
        return "signup";
    }

    //实现注册功能
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String signUp(String name,String password){
        userService.Insert(name, password);
        return "success";
    }
}
