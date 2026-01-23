package com.rafael.itmanager.repository;

import com.rafael.itmanager.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    boolean existsByCpf(String cpf);
}
