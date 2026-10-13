package com.dariochaves.Laboratorio1.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final SecretKey clave;
    private final long expiracionMs;

    public JwtService(@Value("${app.jwt.secret}") String secreto,
                      @Value("${app.jwt.expiration-ms}") long expiracionMs) {
        this.clave = Keys.hmacShaKeyFor(
                secreto.getBytes(StandardCharsets.UTF_8));
        this.expiracionMs = expiracionMs;
    }

    public String generarToken(UserDetails user) {
        String rol = user.getAuthorities()
                .stream()
                .findFirst()
                .map(authority -> authority.getAuthority())
                .orElseThrow(() -> new IllegalArgumentException("El usuario no tiene rol asignado"));

        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + expiracionMs);


        return Jwts.builder()
                .subject(user.getUsername())
                .claim("rol", rol)
                .issuedAt(ahora)
                .expiration(expiracion)
                .signWith(clave)
                .compact();
    }

    public String extraerUsername(String token){
        return extraerClaims(token).getSubject();
    }

    public boolean esTokenValido(String token, UserDetails user) {

      try {
          Claims claims = extraerClaims(token);

          return claims.getSubject().equals(user.getUsername()) && claims.getExpiration().after(new Date());

      } catch (JwtException | IllegalArgumentException e) {
          return false;
      }
    }

    private Claims extraerClaims(String token) {
       return Jwts.parser()
               .verifyWith(clave)
               .build()
               .parseSignedClaims(token)
               .getPayload();
       }
    }

