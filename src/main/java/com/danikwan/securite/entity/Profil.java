package com.danikwan.securite.entity;

import java.util.HashSet;
import com.danikwan.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Table;



@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "profil")
public class Profil extends BaseEntity {

    @Column (nullable = false, unique = true, length = 100)
    private String code;

    @Column (nullable = false)
    private String libelle;

    @Column (nullable = false)
    private boolean nativeProfil;


    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(
            name = "profil_privilege",
            joinColumns = @JoinColumn(name = "profil_id"),  
            inverseJoinColumns = @JoinColumn(name = "privilege_id"))

            private Set<Privilege> privileges = new HashSet<>();
}
