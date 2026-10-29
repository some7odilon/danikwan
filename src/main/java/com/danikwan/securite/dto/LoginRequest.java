package com.danikwan.securite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public record LoginRequest(@NotBlank String username, @NotBlank String password) {}