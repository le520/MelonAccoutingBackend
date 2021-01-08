package com.nwpu.melonbookkeeping.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nwpu.melonbookkeeping.entity.User;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import java.security.Signature;

public class TokenProvider {
    private static final String key = "jKgXoq3cXze6dKvsOUP8nTGjhT1uTspd";

    public static String getToken(User user) {
        return JWT.create()
                .withSubject(user.getUserName())
                .withClaim("userId", user.getId())
                .sign(Algorithm.HMAC256(key));
    }

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

    public static int getUserId(String token) {
        if (verifyToken(token)) {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asInt();
        } else {
            return -1;
        }
    }
}
