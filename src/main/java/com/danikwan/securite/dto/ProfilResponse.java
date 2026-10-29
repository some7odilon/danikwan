package com.danikwan.securite.dto;

import java.util.List;
import java.util.UUID;

public record ProfilResponse(
        UUID id, String code, String libelle, boolean nativeProfil, List<String> privileges) {}