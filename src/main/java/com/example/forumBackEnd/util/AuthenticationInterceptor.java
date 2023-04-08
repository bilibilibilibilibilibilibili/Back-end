package com.example.forumBackEnd.util;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
//        查看请求中是否存在token，如果不存在直接跳转到登陆页面
        String token = tokenUtil.getToken(request);
        if (StringUtils.isEmpty(token)) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
