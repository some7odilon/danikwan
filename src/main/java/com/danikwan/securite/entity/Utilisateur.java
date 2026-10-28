package com.danikwan.securite.entity;

import com.danikwan.common.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "utilisateur")
public class Utilisateur extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    /** Toujours stocké haché (BCrypt), jamais exposé dans un DTO. */
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Column(length = 30)
    private String telephone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean actif;

    @Column(length = 100)
    private String activationKey;

    private Instant confirmationExpireDate;

    @Column(length = 100)
    private String resetKey;

    private Instant resetDate;

    private Instant resetExpireDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profil_id")
    private Profil profil;
}