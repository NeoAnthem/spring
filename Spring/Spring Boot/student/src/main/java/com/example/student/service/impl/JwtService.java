package com.example.student.service.impl;

import com.example.student.dto.LoginDto;
import com.example.student.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.websocket.Decoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${spring.secret}")
    private String secret;
    public String generateToken(LoginDto loginDto,String role) {
        Map<String,Object>claims=new HashMap<>();
        claims.put("role",role);
        return createToken(claims,loginDto.getUserName());
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .subject(userName)
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(generateSignupKey())
                .compact();

    }

    private SecretKey generateSignupKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    private <T>T extractClaims(String token, Function<Claims, T> claimsresolver){
        final Claims claims=extractAllClaims(token);
return claimsresolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(generateSignupKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String extractUserName(String token){
        return extractClaims(token,Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }
    public boolean isExpire(String token){
        return extractExpiration(token).before(new Date());
    }
    public String extractRole(String token){
        return extractAllClaims(token).get("role",String.class);
    }

    public boolean validateToken(String token, UserDetails userDetails){
        final String username=extractUserName(token);
        return (username.equals(userDetails.getUsername())&&!isExpire(token));
    }


}
