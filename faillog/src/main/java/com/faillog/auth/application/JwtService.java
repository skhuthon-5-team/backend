package com.faillog.auth.application;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    //토큰 생성
    public String createToken(String email) {
        return Jwts.builder()
                //payload에 이메일 저장
                .subject(email)
                .issuedAt(new Date()) //토큰 발급 시간
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //토큰 만료 시간 (1시간)
                .signWith(getSigningKey())
                .compact(); //토큰 문자열로 변환
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
