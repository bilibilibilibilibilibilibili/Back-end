package com.example.forumBackEnd.controller;

import com.example.forumBackEnd.pojo.User;
import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.example.forumBackEnd.service.UserService;
import com.example.forumBackEnd.util.TokenUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        boolean result = userService.createAccount(user);
        if(result) {
            Map<String,String> responseBody = new HashMap<>();
            responseBody.put("token", new TokenUtil().generateToken(user.getEmail()));
            return BasicResponse.getSuccessResponse("注册成功", responseBody);
        }
        return BasicResponse.getFailResponse(SC_NOT_ACCEPTABLE,"注册失败");
    }

    /**
     * 登陆账号++++
     */
    @PostMapping("login")
    public Map<String, Object> loginAccount(User user){
        return  userService.loginAccount(user);
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
