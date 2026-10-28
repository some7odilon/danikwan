package com.danikwan.securite.entity;

import com.danikwan.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table (name = "privilege")

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Privilege extends BaseEntity {

    @Column (nullable = false, unique = true, length = 100)
    private String Code;

    @Column (nullable = false)
    private String libelle;

}
