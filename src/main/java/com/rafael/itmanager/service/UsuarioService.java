package com.rafael.itmanager.service;

import com.rafael.itmanager.dto.UsuarioDTOs.UsuarioRequestDTO;
import com.rafael.itmanager.dto.UsuarioDTOs.UsuarioResponseDTO;
import com.rafael.itmanager.mapper.UsuarioMapper;
import com.rafael.itmanager.model.Grupo;
import com.rafael.itmanager.model.Usuario;
import com.rafael.itmanager.model.UsuarioGrupo;
import com.rafael.itmanager.repository.GrupoRepository;
import com.rafael.itmanager.repository.UsuarioGrupoRepository;
import com.rafael.itmanager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final GrupoRepository grupoRepository;
    private final UsuarioGrupoRepository  usuarioGrupoRepository;
    private final PasswordEncoder encoder;
    private final UsuarioMapper mapper;

    @Transactional
    public UsuarioResponseDTO salvar(UsuarioRequestDTO dto, List<String> grupos) {
        Usuario usuario = mapper.toEntity(dto);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);

        List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream()
                .map(nomeGrupo -> {
                    Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);

                    if (possivelGrupo.isPresent()) {
                        Grupo grupo = possivelGrupo.get();
                        return new UsuarioGrupo(usuario, grupo);
                    }

                    return null;

                })      .filter(grupo -> grupo != null)
                        .collect(Collectors.toList());

        usuarioGrupoRepository.saveAll(listaUsuarioGrupo);

        return mapper.toDTO(usuario);
    }

    public Usuario obterPorLogin(String login) {
        Optional<Usuario> usuarioOptional = repository.findByLogin(login);

        if(usuarioOptional.isEmpty()) {
            return null;
        }

        Usuario usuario = usuarioOptional.get();
        List<String> permissoes = usuarioGrupoRepository.findPermissoesByUsuario(usuario);
        usuario.setPermissoes(permissoes);

        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        return this.repository.findAll();
    }
}
