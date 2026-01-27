package com.rafael.itmanager.service;

import com.rafael.itmanager.dto.UsuarioRequestDTO;
import com.rafael.itmanager.dto.UsuarioResponseDTO;
import com.rafael.itmanager.mapper.UsuarioMapper;
import com.rafael.itmanager.model.Usuario;
import com.rafael.itmanager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;
    private final UsuarioMapper mapper;

    public UsuarioResponseDTO salvar(UsuarioRequestDTO dto) {
        Usuario usuario = mapper.toEntity(dto);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);
        return mapper.toDTO(usuario);
    }

    public UsuarioResponseDTO obterPorLogin(String login) {
        Usuario usuario = repository.findByLogin(login);
        return mapper.toDTO(usuario);
    }

    public Usuario obterEntidadePorLogin(String login) {
        return repository.findByLogin(login);
    }
}
