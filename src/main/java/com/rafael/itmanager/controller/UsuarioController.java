package com.rafael.itmanager.controller;

import com.rafael.itmanager.dto.UsuarioDTOs.UsuarioRequestDTO;
import com.rafael.itmanager.dto.UsuarioDTOs.UsuarioResponseDTO;
import com.rafael.itmanager.mapper.UsuarioMapper;
import com.rafael.itmanager.model.Usuario;
import com.rafael.itmanager.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvar(@RequestBody UsuarioRequestDTO dto ) {
        UsuarioResponseDTO salvar = service.salvar(dto, dto.permissoes());
        return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios( ) {
        List<Usuario> usuarios = service.listarUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/logado")
    public ResponseEntity<String> rotaLogin(Authentication authentication) {
        return ResponseEntity.ok("Usuario conectado: " + authentication.getName());
    }
}
