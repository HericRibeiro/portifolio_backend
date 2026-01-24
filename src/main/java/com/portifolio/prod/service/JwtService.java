package com.portifolio.prod.service;

import com.portifolio.prod.model.UserModel;
import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portifolio.prod.config.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Autowired
    private JwtProperties jwtProperties;
    
    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getJwt().getSecret().getBytes());
    }

    public String generateToken(String mail) {
        return Jwts.builder()
            .setSubject(mail)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getJwt().getExpiration()))
            .signWith(getSecretKey(), SignatureAlgorithm.HS256)
            .compact();
    }
    
    public String generateTokenWithUser(UserModel user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("mail", user.getMail());
        claims.put("role", user.getRole().toString());

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(user.getMail())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getJwt().getExpiration()))
            .signWith(getSecretKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public String extractMail(String token) {
        return extractClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    public Long extractId(String token) {
        return extractClaims(token).get("id", Long.class);
    }

    public boolean validToken(String token, String mail) {
        String mailOfToken = extractMail(token);
        return (mailOfToken.equals(mail) && !tokenExpiration(token));
    }

    public boolean tokenExpiration(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSecretKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}