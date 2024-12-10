package com.gabrielledezma.foro.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gabrielledezma.foro.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(apiSecret);
            var token = JWT.create()
                    .withIssuer("Foro")
                    .withSubject(usuario.getNombre())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algoritmo);
            return token;
        } catch (JWTCreationException exception){
            throw  new RuntimeException("Error al generar el token JWT");
        }
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String token) {
        try {
            var algoritmo = Algorithm.HMAC256(apiSecret);
            JWTVerifier verifier = JWT.require(algoritmo)
                    .withIssuer("Foro")
                    .build();
            var decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Error al decodificar el token");
        }
    }
}
