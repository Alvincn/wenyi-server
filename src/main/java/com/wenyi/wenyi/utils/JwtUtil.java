package com.wenyi.wenyi.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wenyi.wenyi.entity.Roots;
import com.wenyi.wenyi.entity.User;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    // Token过期时间24小时
    public static final long EXPIRE_TIME = 60L * 60 * 1000 * 24 * 30;
    public static final String SECRET = "wenyiyyds";
    /**
     * @Author csb
     * @Param token
     * @Param username
     * @Param secret
     * @Return boolean
     */
    public static boolean verify(String token, Integer id, String secret) {
        try {
            // 设置加密算法
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            // 验证Token，生成一个解析后的JWT对象
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            Claim user = claims.get("user");
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    /**
     * @Author csb
     * 生成签名
     * @Param [username, secret]
     * @Return java.lang.String
     */
    public static String sign(User user) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("username", user.getUsername());
        claims.put("id", user.getId());
        // 加密算法
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带 id 信息
        return JWT.create()
                .withClaim("user", claims) //添加荷载
                .withExpiresAt(date) // 添加获取时间
                .sign(algorithm);

    }

    public static String sign(Roots root) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("username", root.getUsername());
        claims.put("id", root.getId());
        // 加密算法
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带 id 信息
        return JWT.create()
                .withClaim("user", claims) //添加荷载
                .withExpiresAt(date) // 添加获取时间
                .sign(algorithm);

    }

    /**
     * 解密获取id
     * @Author csb
     * @Param [request]
     * @Return java.lang.String
     */
    public static User getUserNameByToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        // 验证Token，生成一个解析后的JWT对象
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        return claims.get("user").as(User.class);
    }

    public static Roots getRootsUserNameByToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        // 验证Token，生成一个解析后的JWT对象
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        return claims.get("user").as(Roots.class);
    }
}
