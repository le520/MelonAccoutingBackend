package com.nwpu.melonbookkeeping.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nwpu.melonbookkeeping.entity.User;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import java.security.Signature;

/**
 * Token工具类，用于生成和验证token
 */
public class TokenProvider {
    private static final String key = "jKgXoq3cXze6dKvsOUP8nTGjhT1uTspd";

    /**
     * 获取一个Token
     *
     * @param user 用户
     * @return token
     */
    public static String getToken(User user) {
        return JWT.create()
                .withSubject(user.getUserName())
                .withClaim("userId", user.getId())
                .sign(Algorithm.HMAC256(key));
    }

    /**
     * 验证一个token
     *
     * @param token 要验证的token
     * @return 验证结果
     */
    public static boolean verifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(key))
                .build();
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取token存储的用户的id
     *
     * @param token token
     * @return 用户的id
     */
    public static int getUserId(String token) {
        if (verifyToken(token)) {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asInt();
        } else {
            return -1;
        }
    }
}
