package org.riskfinderteam.riskfinder.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.riskfinderteam.riskfinder.auth.enums.Role;
import org.riskfinderteam.riskfinder.common.exception.BaseException;
import org.riskfinderteam.riskfinder.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret")
    private String secretKey;

    @Value("${jwt.AccessExpiration")
    private  long AccessExpiration;

    @Value("${jwt.refreshExpiration")
    private  long refreshExpiration;

    private SecretKey key;

    //SecretKey 객체를 초기화하는 메서드
    @PostConstruct
    protected void init(){
        if(secretKey == null || secretKey.isBlank()){
            throw new BaseException(ErrorCode.JWT_NOT_CONFIGURED);
        }

        byte[] keyBytes = Base64.getEncoder().encode(secretKey.getBytes());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    //AccessToken 생성
    public String createAccessToken(Long userId, Role role){
        Date now = new Date();
        Date expiry = new Date(now.getTime() + AccessExpiration);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("role", role.name())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    //RefreshToken 생성
    public String createRefreshToken(Long userId){
        Date now = new Date();
        Date expiry = new Date(now.getTime() + refreshExpiration);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

}
