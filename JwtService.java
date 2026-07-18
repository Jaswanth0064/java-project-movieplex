package com.movieplex.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    // Secret key (We'll move it to application.properties later)
    private static final String SECRET =
            "MoviePlexSecretKeyMoviePlexSecretKey123456";

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
    
    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 ))
                .signWith(getSignKey())
                .compact();
    }
    public String extractEmail(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateToken(String token, String email) {

        String extractedEmail = extractEmail(token);

        return extractedEmail.equals(email);
    }

   
    

}