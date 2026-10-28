package com.danikwan.securite.repository;

import com.danikwan.securite.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, UUID> {
    Optional<Utilisateur> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}