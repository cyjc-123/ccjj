package org.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "amF2YXdlYmFpd2FuZ2NodW55YW4yMDI2eWVhcjA0MTg=";
    private static Long expire = 43200000L;

    /**
     * 生成JWT令牌
     * @return
     */
    public static String generateJwt(Map<String,Object> claims){
        byte[] decodedKey = Base64.getDecoder().decode(signKey);
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(Keys.hmacShaKeyFor(decodedKey))
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        byte[] decodedKey = Base64.getDecoder().decode(signKey);
        Claims claims = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(decodedKey))
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
