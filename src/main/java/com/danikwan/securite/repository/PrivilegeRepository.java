package com.danikwan.securite.repository;

import com.danikwan.securite.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {
}