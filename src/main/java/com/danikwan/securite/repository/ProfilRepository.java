package com.danikwan.securite.repository;

import com.danikwan.securite.entity.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface ProfilRepository extends JpaRepository<Profil, UUID> {
    Optional<Profil> findByCode(String code);
}