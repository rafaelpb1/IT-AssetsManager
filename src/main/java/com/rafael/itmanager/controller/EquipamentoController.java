package com.rafael.itmanager.controller;

import com.rafael.itmanager.dto.EquipamentoDTOs.EquipamentoRequestDTO;
import com.rafael.itmanager.dto.EquipamentoDTOs.EquipamentoResponseDTO;
import com.rafael.itmanager.dto.EquipamentoDTOs.EquipamentoStatusDTO;
import com.rafael.itmanager.service.EquipamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EquipamentoResponseDTO> cadastroEquipamento(@RequestBody @Valid EquipamentoRequestDTO dto) {
        var equipamento = service.salvarEquipamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipamento);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<EquipamentoResponseDTO>> listarEquipamentos() {
        return ResponseEntity.ok(service.listarEquipamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoResponseDTO> buscarEquipamentoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarEquipamentoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipamentoResponseDTO> atualizarEquipamento(@PathVariable Long id, @RequestBody @Valid EquipamentoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizarEquipamento(id, dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<EquipamentoResponseDTO> atualizarStatusEquipamento(@PathVariable Long id,
                                                                             @RequestBody @Valid EquipamentoStatusDTO novoStatus) {
        return ResponseEntity.ok(service.atualizarStatus(id, novoStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEquipamento(@PathVariable Long id) {
        service.excluirEquipamento(id);
        return ResponseEntity.noContent().build();
    }
}
