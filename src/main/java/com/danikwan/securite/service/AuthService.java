package com.danikwan.securite.service;

import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danikwan.securite.dto.LoginRequest;
import com.danikwan.securite.dto.LoginResponse;
import com.danikwan.securite.entity.Utilisateur;
import com.danikwan.securite.repository.UtilisateurRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {

    Utilisateur utilisateur = utilisateurRepository.findByUsername(request.username())
            .orElseThrow(() -> new BadCredentialsException("Identifiants invalides"));

    if (!passwordEncoder.matches(request.password(), utilisateur.getPassword())) {
        throw new BadCredentialsException("Identifiants invalides");
    }

    if (!utilisateur.isActif()) {
        throw new DisabledException("Utilisateur inactif");
    }

    String profil = utilisateur.getProfil().getCode();
    List<String> privileges = utilisateur.getProfil().getPrivileges().stream()
            .map(privilege -> privilege.getCode())
            .sorted()
            .toList();

    JwtService.Token token = jwtService.generer(utilisateur.getUsername(), profil, privileges);

    return new LoginResponse(token.value(), "Bearer", token.expiresInSeconds(),
            utilisateur.getUsername(), profil, privileges);
}
}