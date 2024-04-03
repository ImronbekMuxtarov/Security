package com.example.securitychains.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.securitychains.entity.Roles;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Component
public class JwtService {

    public String generateToken(Long  id, String username, Set<Roles> roles){
        return JWT.create()
                .withSubject(String.valueOf(id))
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(Duration.ofHours(1)))
                .withClaim("username",username)
                .withClaim("roles", roles.stream().map(role -> role.getRole().name()).toList())
                .sign(Algorithm.HMAC256("secret"));
    }

    public DecodedJWT decode(String token){
        return JWT.require(Algorithm.HMAC256("secret"))
                .build()
                .verify(token);
    }

}
