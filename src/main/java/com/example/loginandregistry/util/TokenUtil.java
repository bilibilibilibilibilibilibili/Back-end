package com.example.loginandregistry.util;

import com.example.loginandregistry.pojo.User;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        token = JWT.create().withAudience(String.valueOf(user.getId()))
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

}
