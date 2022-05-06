package com.example.score.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {

    private long expire;
    private String secret;
    private String header;

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String createToken(UserDetails userDetails) {
        return this.createToken(userDetails.getUsername());
    }

    /**
     * 生成JWT
     * @param username
     * @return
     */
    public String createToken(String username) {
        //生成JWT
        return Jwts.builder()
                //jwt 私有声明
                //.claim("userContext", JSON.toJSONString(claim))
                //jwt 的主题
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expire * 60 * 1000))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    //解析JWT
    public Claims getClaimsByToken(String token) {
        Claims claims = null;
        try {
            claims =  Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token)
                        .getBody();
        } catch (Exception e) {
            log.error("JWT反解析失败, token已过期或不正确, token : {}", token);
        }
        return claims;
    }

    //判断JWT是否过期
    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    //从token中获取主题
    public String getSubjectFromToken(String token) {
        Claims claims = this.getClaimsByToken(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }

    //校验token
    public boolean validateToken(String token, UserDetails userDetails) {
        Claims claims = this.getClaimsByToken(token);
        return claims.getSubject().equals(userDetails.getUsername()) && !isTokenExpired(claims);
    }
}
