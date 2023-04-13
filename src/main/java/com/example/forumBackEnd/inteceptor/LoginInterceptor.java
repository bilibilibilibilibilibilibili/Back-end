package com.example.forumBackEnd.inteceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.forumBackEnd.PassToken;
import com.example.forumBackEnd.UserLoginToken;
import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.example.forumBackEnd.service.UserService;
import com.example.forumBackEnd.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

/**
 * &#064;title  AuthenticationInterceptor
 * &#064;description  认证请求头中的token的拦截器类
 *
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        String token = request.getHeader("token");
        tokenUtil.verifyToken(token);
        if (TokenUtil.result) {
//            String email = tokenUtil.getEmailFromToken(token);
//            request.setAttribute("email", email);
            return true;
        } else {
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(BasicResponse.getFailResponse(SC_UNAUTHORIZED,TokenUtil.message));
            response.setStatus(SC_UNAUTHORIZED);
            response.getWriter().write(data);
            return false;
        }
    }
}
