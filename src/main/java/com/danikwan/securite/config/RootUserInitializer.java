package com.danikwan.securite.config;

import com.danikwan.securite.entity.Profil;
import com.danikwan.securite.entity.Utilisateur;
import com.danikwan.securite.repository.ProfilRepository;
import com.danikwan.securite.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RootUserInitializer implements ApplicationRunner {

    private final UtilisateurRepository utilisateurRepository;
    private final ProfilRepository profilRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.security.root.username}")
    private String username;

    @Value("${app.security.root.email}")
    private String email;

    @Value("${app.security.root.password:}")
    private String password;

    @Override
    public void run(ApplicationArguments args) {
        if (password == null || password.isBlank()) {
            log.warn("ROOT_PASSWORD non défini : aucun utilisateur ROOT créé.");
            return;
        }
        if (utilisateurRepository.existsByUsername(username)) {
            return;
        }
        Profil profilRoot = profilRepository.findByCode("ROOT")
                .orElseThrow(() -> new IllegalStateException("Profil ROOT introuvable : migration V2 appliquée ?"));

        Utilisateur root = new Utilisateur();
        root.setUsername(username);
        root.setPassword(passwordEncoder.encode(password));
        root.setNom("Super");
        root.setPrenom("Administrateur");
        root.setEmail(email);
        root.setActif(true);
        root.setProfil(profilRoot);
        utilisateurRepository.save(root);
        log.info("Utilisateur ROOT créé : {}", username);
    }
}