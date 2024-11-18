package com.academia.equipamentos.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /**
     * Gera um token JWT a partir do nome de usuário.
     *
     * @param username Nome do usuário para gerar o token.
     * @return Token JWT.
     */
    public String generateToken(String username) {
        Date expirationDate = new Date(System.currentTimeMillis() + jwtExpiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Valida o token JWT.
     *
     * @param token Token JWT a ser validado.
     * @return true se o token for válido, caso contrário, false.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extrai o nome de usuário de um token JWT.
     *
     * @param token Token JWT.
     * @return Nome de usuário contido no token.
     */
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
