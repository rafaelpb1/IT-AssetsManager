package com.rafael.itmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rh")
public class RhController {

    @GetMapping("/tecnico")
    @PreAuthorize("hasAnyRole('TECNICO_RH', 'ADMIN', 'COORDENADOR')")
    public ResponseEntity<String> tecnico() {
        return ResponseEntity.ok("Rota do técnico");
    }

    @GetMapping("/coordenador")
    @PreAuthorize("hasAnyRole('COORDENADOR', 'ADMIN')")
    public ResponseEntity<String> coordenador() {
        return ResponseEntity.ok("Rota do coordenador");
    }


}
