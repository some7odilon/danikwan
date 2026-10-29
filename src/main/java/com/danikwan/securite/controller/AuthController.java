package com.danikwan.securite.controller;

import com.danikwan.securite.dto.LoginRequest;
import com.danikwan.securite.dto.LoginResponse;
import com.danikwan.securite.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    /** Renvoie l'identité contenue dans le token : pratique pour tester. */
    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
                "username", jwt.getSubject(),
                "profil", jwt.getClaimAsString("profil"),
                "privileges", jwt.getClaimAsStringList("privileges"));
    }
}