package com.danikwan.securite.dto;

import java.util.List;

public record LoginResponse(
        String accessToken,
        String tokenType,
        long expiresIn,
        String username,
        String profil,
        List<String> privileges) {}