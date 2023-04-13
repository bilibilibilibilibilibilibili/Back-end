package com.example.forumBackEnd.controller;

import com.example.forumBackEnd.pojo.User;
import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.example.forumBackEnd.service.UserService;
import com.example.forumBackEnd.util.TokenUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_ACCEPTABLE;

@RestController
@RequestMapping(path="user",produces = "application/json; charset=UTF-8")
public class UserController {

    @Resource
    private UserService userService;

    /**
     *  使用邮箱注册账号
     * @param user
     * @return
     */
    @PostMapping("register-by-email")
    public BasicResponse createAccount(@RequestBody User user){
        int result = userService.createAccount(user);
        switch (result) {
            case 0 -> {
                Map<String,String> responseBody = new HashMap<>();
                responseBody.put("token", new TokenUtil().generateToken(user.getEmail()));
                return BasicResponse.getSuccessResponse("注册成功", responseBody);
            }
            case 1 -> {
                return BasicResponse.getFailResponse(SC_INTERNAL_SERVER_ERROR,"注册失败");
            }
        }
        return BasicResponse.getFailResponse(SC_NOT_ACCEPTABLE,"邮箱被占用");
    }

    /**
     * 登陆账号
     */
    @PostMapping("login-by-email")
    public BasicResponse loginAccount(@RequestBody User user){
        int result = userService.loginAccount(user);
        switch (result) {
            case 0 -> {
                return BasicResponse.getFailResponse(SC_NOT_ACCEPTABLE,"找不到用户");
            }
            case 1 -> {
                return BasicResponse.getFailResponse(SC_NOT_ACCEPTABLE,"密码错误");
            }
            case 2 -> {
                Map<String,String> responseBody = new HashMap<>();
                responseBody.put("token", new TokenUtil().generateToken(user.getEmail()));
                return BasicResponse.getSuccessResponse("登录成功", responseBody);
            }
        }
        System.out.println("服务器内部错误");
        return BasicResponse.getFailResponse(SC_INTERNAL_SERVER_ERROR, "服务器出现内部错误");
    }

    /**
     * 激活账号
     * @param confirmCode
     * @return
     */
    @GetMapping("activation")
    public Map<String,Object> activationAccount(String confirmCode){
        return userService.activationAccount(confirmCode);
    }
}
