package com.bit.blog.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Map;

@Slf4j
public class JwtUtils {

    private static String SECRET_STRING = "kGmiTrem5gU1+BD0lwPssDpkP50fNObF/wygI8oEPTk=";
    private static Key key=Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_STRING));
    public static String genToken(Map<String, Object> claims) {
        String compat = Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
        return compat;
    }
    public static Claims parseToken(String token) {
        if(!StringUtils.hasLength(token)){
            return null;
        }
        JwtParser build=Jwts.parserBuilder().setSigningKey(key).build();
        Claims claims=null;
        try {
            claims=build.parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error("token解析失败,token"+token);
        }
        build.parseClaimsJws(token).getBody();
        return claims;
    }

}
