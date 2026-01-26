package com.rafael.itmanager.controller;

import com.rafael.itmanager.dto.EmprestimoRequestDTO;
import com.rafael.itmanager.dto.EmprestimoResponseDTO;
import com.rafael.itmanager.model.Emprestimo;
import com.rafael.itmanager.service.EmprestimoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService service;

    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> fazerEmprestimo(@RequestBody @Valid EmprestimoRequestDTO dto){
        var emprestimo = service.realizarEmprestimo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimo);
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoResponseDTO>> listarEmprestimos(){
        return ResponseEntity.ok(service.listarEmprestimos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDTO>  buscarEmprestimoPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarEmprestimoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDTO> atualizarEmprestimo(@PathVariable Long id, @RequestBody @Valid EmprestimoRequestDTO dto){
        return ResponseEntity.ok(service.atualizarEmprestimo(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEmprestimo(@PathVariable Long id){
        service.excluirEmprestimo(id);
        return ResponseEntity.noContent().build();
    }
}
