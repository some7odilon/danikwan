package com.danikwan.securite.controller;

import com.danikwan.securite.dto.ProfilResponse;
import com.danikwan.securite.service.ProfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profils")
@RequiredArgsConstructor
public class ProfilController {

    private final ProfilService profilService;

    @GetMapping
    @PreAuthorize("hasAuthority('PROFIL_LIRE')")
    public List<ProfilResponse> lister() {
        return profilService.lister();
    }
}