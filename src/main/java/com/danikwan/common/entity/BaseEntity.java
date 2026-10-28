package com.danikwan.common.entity;

import org.springframework.data.annotation.LastModifiedDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.*;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @CreatedDate 
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate 
    @Column (nullable = false)
    private Instant updatedAt;
}
