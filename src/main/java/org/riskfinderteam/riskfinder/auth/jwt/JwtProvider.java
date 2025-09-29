package org.riskfinderteam.riskfinder.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.riskfinderteam.riskfinder.auth.enums.Role;
import org.riskfinderteam.riskfinder.auth.security.CustomUserDetails;
import org.riskfinderteam.riskfinder.common.exception.BaseException;
import org.riskfinderteam.riskfinder.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.AccessExpiration}")
    private  long AccessExpiration;

    @Value("${jwt.refreshExpiration}")
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

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public Authentication getAuthentication(String token){
        Claims claims = getClaims(token);

        Long userId = Long.parseLong(claims.getSubject());
        String email = claims.get("email", String.class);
        String roleString = claims.get("role", String.class);

        if(roleString == null || roleString.trim().isEmpty()){
            throw new JwtException(" role 클레임이 없습니다.");
        }

        Role roleEnum = Role.valueOf(roleString);
        List<SimpleGrantedAuthority> authorities  = List.of(new SimpleGrantedAuthority(roleEnum.name()));

        CustomUserDetails userDetails = new CustomUserDetails(userId, email, roleEnum, authorities);

        return new UsernamePasswordAuthenticationToken(userDetails,token, authorities);
    }

    public Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
