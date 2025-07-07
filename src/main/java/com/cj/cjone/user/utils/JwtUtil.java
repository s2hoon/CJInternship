package com.cj.cjone.user.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cj.cjone.user.User;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    private final SecretKey secretKey;

    @Value("${jwt.access-token-expiration}")
    private Long accessExpiration;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String createAccessToken(User user) {
        return Jwts.builder()
                .claim("username", user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(secretKey)
                .compact();
    }

}
