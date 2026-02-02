package com.roima.restapi.security;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private String SECRET  = "why_should_itell_y0u_mann_09alaod";

    public String generateToken(String username) {
        // Generate a secure key from your secret string
        // The key length must be sufficient for HS256 (at least 256 bits or 32 bytes)
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));


        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                .signWith(key, SignatureAlgorithm.HS256) // Use the non-deprecated method
                .compact();
    }
}
