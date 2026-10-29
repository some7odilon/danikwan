package com.danikwan.securite.service;

import com.danikwan.securite.dto.ProfilResponse;
import com.danikwan.securite.entity.Privilege;
import com.danikwan.securite.repository.ProfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfilService {

    private final ProfilRepository profilRepository;

    @Transactional(readOnly = true)
    public List<ProfilResponse> lister() {
        return profilRepository.findAll().stream()
                .map(p -> new ProfilResponse(p.getId(), p.getCode(), p.getLibelle(), p.isNativeProfil(),
                        p.getPrivileges().stream().map(Privilege::getCode).sorted().toList()))
                .toList();
    }
}

