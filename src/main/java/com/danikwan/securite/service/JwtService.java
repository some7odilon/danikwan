package com.danikwan.securite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;


@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtEncoder jwtEncoder;

    @Value("${app.security.jwt.expiration-minutes}")
    private long expirationMinutes;


    public record Token(String value, long expiresInSeconds) {}

    public Token generer(String username, String profil, List<String> privileges) {

        Instant now = Instant.now();
        Duration validite = Duration.ofMinutes(expirationMinutes);
        

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("danikwan")
                .issuedAt(now)
                .expiresAt(now.plus(validite))
                .subject(username)
                .claim("profil", profil)
                .claim("privileges", privileges)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
        String valeurToken = jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
        return new Token(valeurToken, validite.getSeconds());


    }
    
}
