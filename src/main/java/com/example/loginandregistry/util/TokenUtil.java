package com.example.loginandregistry.util;

import com.example.loginandregistry.pojo.enumClass.Identity;
import com.example.loginandregistry.pojo.User;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenUtil {
    /**
     * 创建map用于存储令牌
     */
    private static Map<String, User> tokenMap = new HashMap<>();


    /**
     * 根据用户名和密码，使用加密算法生成JWT的token令牌。
     * @param user
     * @return
     */
    public String generateToken(User user) {
        String token = "";
        String isAdmin = "";
        Identity identity = user.getIdentity();
        switch(identity){
            case ADMIN,PRIOR_ADMIN,SUPER_ADMIN -> isAdmin = "admin";
            default ->  isAdmin = "false";
        }
        Date start = new Date();
        Date end = new Date(System.currentTimeMillis() + 24*60*60*1000 * 7);    // 7天免登陆
        token = JWT.create().withAudience(String.valueOf(user.getId()))
                .withAudience(isAdmin)
//                .withIssuedAt(start)
//                .withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

}
