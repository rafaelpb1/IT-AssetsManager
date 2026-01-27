package com.rafael.itmanager.controller;

import com.rafael.itmanager.dto.UsuarioRequestDTO;
import com.rafael.itmanager.dto.UsuarioResponseDTO;
import com.rafael.itmanager.mapper.UsuarioMapper;
import com.rafael.itmanager.model.Usuario;
import com.rafael.itmanager.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvar(@RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO salvar = service.salvar(dto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(salvar);
    }
}
