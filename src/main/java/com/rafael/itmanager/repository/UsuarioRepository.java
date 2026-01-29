package com.rafael.itmanager.repository;

import com.rafael.itmanager.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Usuario findByLogin(String login);
}
