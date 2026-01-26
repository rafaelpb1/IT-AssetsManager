package com.rafael.itmanager.controller;

import com.rafael.itmanager.dto.ColaboradorRequestDTO;
import com.rafael.itmanager.dto.ColaboradorResponseDTO;
import com.rafael.itmanager.service.ColaboradorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/colaboradores")
@RequiredArgsConstructor
public class ColaboradorController {

    private final ColaboradorService service;

    @PostMapping
    public ResponseEntity<ColaboradorResponseDTO> cadastrarColaborador(@RequestBody @Valid ColaboradorRequestDTO dto) {
         var colaborador = service.salvarColaborador(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(colaborador);
    }

    @GetMapping
    public ResponseEntity<List<ColaboradorResponseDTO>> listarColaboradores() {
        return ResponseEntity.ok(service.listarColaboradores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorResponseDTO> buscarColaboradorPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.buscarColaboradorPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirColaborador(@PathVariable("id") Long id) {
        service.excluirColaborador(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorResponseDTO> atualizarColaborador(@PathVariable("id") Long id, @RequestBody @Valid ColaboradorRequestDTO dto) {
        return ResponseEntity.ok(service.atualizarColaborador(id, dto));
    }
}
