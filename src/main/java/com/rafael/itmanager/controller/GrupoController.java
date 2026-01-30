package com.rafael.itmanager.controller;

import com.rafael.itmanager.model.Grupo;
import com.rafael.itmanager.repository.GrupoRepository;
import com.rafael.itmanager.service.GrupoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
@RequiredArgsConstructor
public class GrupoController {

    private final GrupoRepository repository;
    private final GrupoService grupoService;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Grupo> salvar(@RequestBody Grupo grupo) {
        grupoService.salvar(grupo);
        return ResponseEntity.ok(grupo);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Grupo>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@RequestParam String id) {
        grupoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
