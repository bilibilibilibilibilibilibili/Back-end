package com.example.forumBackEnd.inteceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.forumBackEnd.PassToken;
import com.example.forumBackEnd.UserLoginToken;
import com.example.forumBackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @title:AuthenticationInterceptor
 * @description:认证请求头中的token的拦截器类
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    /**
     * 执行目标方法之前拦截验证Token
     * @param httpServletRequest
     * @param httpServletResponse
     * @param object
     * @return
     * @throws Exception
     */
//    重写方法，实现拦截
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Object object)
            throws Exception{
        //从http请求头中取出token
        String token=httpServletRequest.getHeader("token");
        //如果不是映射到方法(即-路径下的方法)直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod) object;
        //拿到方法头部的注解
        Method method=handlerMethod.getMethod();
        //检查是否有PassToken注释，有则跳过认证
        if(method.isAnnotationPresent(PassToken.class)){
            //进入到PassToken的注解中，查看里面required的值是否为true（默认true）
            PassToken passToken=method.getAnnotation(PassToken.class);
            if(passToken.required()){
                return true;
            }
        }

        //检查是否有UserLoginToken注释，有则认证
        if(method.isAnnotationPresent(UserLoginToken.class)){
            UserLoginToken userLoginToken=method.getAnnotation(UserLoginToken.class);
            if(userLoginToken.required()){
                //执行认证
                //如果没有token，则代表当前状态为未登录
                if(token==null){
                    throw new RuntimeException("无token，请重新登录");
                }
                //如果有token，则取出token中的id和pwd
                int id;
                String pwd;
                try{
                    //解密token，取出第0个参数（即-存入的id）
                    id= Integer.parseInt(JWT.decode(token).getAudience().get(0));
                    System.out.println("id的值"+id);
                }catch (JWTDecodeException j){
                    //若取出过程出错，则报一个错误
                    throw new RuntimeException("401");
                }
                //成功取出，则调用service层的通过用户名获取id的方法，判断得到的数据账号是否存在
                String userPwd= userService.selectPasswordById(id);

                if(userPwd.length()==0){
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                //如果账号存在，此时验证密码，查看数据库中的密码是否和token中保存的密码一致
                JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(userPwd)).build();
                try {
                    jwtVerifier.verify(token);
                }catch (JWTVerificationException e){
                    throw new RuntimeException("401");
                }
            }else{
                throw new RuntimeException("无认证方法无法访问");
            }
            return true;
        }
        return true;
    }
}
