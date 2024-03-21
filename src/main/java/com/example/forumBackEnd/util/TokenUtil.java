package com.example.forumBackEnd.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.forumBackEnd.pojo.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.List;

@Component
public class TokenUtil{
    private final static String secret = "ZW1sYXRiYW56YWlp";
    private final static long expiration = 604800;  // 单位：秒
    private final static String issuer = "emlat";
    public static Boolean result = false;
    public static String message = "";

    /**
     * 生成token
     * @param id userid
     * @return token
     */
    public static String generateToken(int id) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiration*1000);
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("userId",id)
                .withIssuedAt(now)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public static void verifyToken(String token){
        try {
            if (null == token || token.equals("")) {
                result = false;
                message = "token is empty";
                return;
            }

            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)  //verifier 可复用
                    .withIssuer(issuer) // 设定token发布者
                    .build();
            DecodedJWT jwt = verifier.verify(token);  //解码jwt
            String decodeIssuer = jwt.getIssuer();

            // 验证自定义参数
            int userId = jwt.getClaim("userId").asInt();
            if (userId<=0) {
                result = false;
                message = "invalid user info";
                return;
            }
            // todo 进一步验证
        }catch (TokenExpiredException e){
            // token过期
            result = false;
            message = "token is expired";
            e.printStackTrace();
            return;
        }catch (SignatureVerificationException e){
            result = false;
            message = "invalid signature";
            e.printStackTrace();
            return;
        }catch (AlgorithmMismatchException e){
            result = false;
            message = "Algorithm inconsistent";
            e.printStackTrace();
            return;
        }catch (Exception e){
            result = false;
            message = "invalid token";
            e.printStackTrace();
            return;
        }
        result = true;
        message = "token confirm";
    }

    public static int getIdFromToken(String token){
        return JWT.decode(token).getClaim("userId").asInt();
    }

//    /**
//     *
//     * @param token
//     * @param key
//     * @return userId
//     * 获取制定token中某个属性值
//     */
//    public static String get(String token, String key) {
//        List<String> list= JWT.decode(token).getAudience();
//        String userId = JWT.decode(token).getAudience().get(0);
//        return userId;
//    }

//    /**
//     * 获取request
//     * @return
//     */
//    public static HttpServletRequest getRequest() {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes();
//        return requestAttributes == null ? null : requestAttributes.getRequest();
//    }


//    /**
//     *
//     * @param request
//     * @return
//     * 获取token
//     */
//    public String getToken(HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        for (Cookie c :
//                cookies) {
//            if (c.getName().equals("token")) {
//                return c.getValue();
//            }
//        }
//        return null;
//    }
}