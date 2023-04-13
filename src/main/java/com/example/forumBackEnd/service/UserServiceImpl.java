package com.example.forumBackEnd.service;

import com.example.forumBackEnd.mapper.UserMapper;
import com.example.forumBackEnd.pojo.User;
import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.example.forumBackEnd.util.IUserService;

import com.example.forumBackEnd.util.TokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.executor.ErrorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    TokenUtil tokenUtil;


//    public ErrorContext loginCheck(User user, HttpServletResponse response) {
//        User user2 = (User) userMapper.selectUserByEmail(user.getUseremail());
//        if (user2 == null) {
//            return BasicResponse.error().message("该用户不存在！");
//        }
//        if (!user2.getPassword().equals(user.getPassword())) {
//            return BasicResponse.error().message("密码错误！");
//        }
//        String token = tokenUtil.generateToken(user2);
//        Cookie cookie = new Cookie("token", token);
////        设置cookie的作用域：为”/“时，以在webapp文件夹下的所有应用共享cookie
//        cookie.setPath("/");
//        response.addCookie(cookie);
//        return BasicResponse.ok().message("登录成功！");
//    }

    @Override
    public void sayHello() {
        System.out.println("Hello" );
    }
}

