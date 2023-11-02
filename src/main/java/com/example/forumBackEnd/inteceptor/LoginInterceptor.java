package com.example.forumBackEnd.inteceptor;

import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.example.forumBackEnd.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

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
            int userId = tokenUtil.getIdFromToken(token);
            request.setAttribute("userId", userId);
            return true;
        } else {
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(BasicResponse.getFailResponse(SC_BAD_REQUEST,TokenUtil.message));
            response.setStatus(SC_BAD_REQUEST);
            response.getWriter().write(data);
            return false;
        }
    }
}
