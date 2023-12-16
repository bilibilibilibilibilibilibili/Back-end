package com.example.forumBackEnd.controller;

import com.example.forumBackEnd.inteceptor.LoginAuth;
import com.example.forumBackEnd.pojo.User;
import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.example.forumBackEnd.service.UserService;
import com.example.forumBackEnd.util.TokenUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

import static jakarta.servlet.http.HttpServletResponse.*;

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
//                System.out.println(user.getId());
                responseBody.put("token", new TokenUtil().generateToken(user.getId()));
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
        try{
            switch (result) {
                case 0 -> {
                    return BasicResponse.getFailResponse(SC_NOT_ACCEPTABLE,"找不到用户");
                }
                case -1 -> {
                    return BasicResponse.getFailResponse(SC_NOT_ACCEPTABLE,"密码错误");
                }
                default -> {
//                    System.out.println("userId: "+result);
                    String newToken = new TokenUtil().generateToken(result);
//                    System.out.println("newToken:"+newToken);
                    Map<String,String> responseBody = new HashMap<>();
                    responseBody.put("token", newToken);
                    return BasicResponse.getSuccessResponse("登录成功", responseBody);
                }
            }
        }catch (Exception e){
            System.out.println("服务器内部错误");
            return BasicResponse.getFailResponse(SC_INTERNAL_SERVER_ERROR, "服务器出现内部错误");
        }
    }

    /**
     * 激活账号
     * @return
     */
    @GetMapping("activation")
    @LoginAuth
    public BasicResponse activationAccount(HttpServletRequest request){
        int resultCode = userService.activationAccount(request.getParameter("userId"),
                request.getParameter("confirmCode"));
        switch(resultCode){
            case SC_OK -> { return BasicResponse.getSuccessResponse("激活成功", null); }
            case SC_BAD_REQUEST -> { return BasicResponse.getFailResponse(SC_BAD_REQUEST, "链接已失效，请重新注册"); }
            default -> { return BasicResponse.getFailResponse("由于服务器内部原因，激活失败"); }
        }
    }

    /**
     * 使用token获取用户信息
     * @param request
     * @return 脱密后的User
     */
    @PostMapping("get-user-info-by-token")
    @LoginAuth
    public BasicResponse getUserInfoByToken(HttpServletRequest request){
        int userId = (int) request.getAttribute("userId");  // 拦截器自动取出了userId
        if (userId>0){
            User user = userService.getUserById(userId);
            if (user!=null){
                return BasicResponse.getSuccessResponse(user);
            }else {
                return BasicResponse.getFailResponse("找不到用户");
            }
        } else {
            return BasicResponse.getFailResponse("无效token");
        }
    }

}
