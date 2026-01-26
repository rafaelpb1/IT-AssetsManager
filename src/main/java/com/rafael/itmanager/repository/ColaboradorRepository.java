package com.rafael.itmanager.repository;

import com.rafael.itmanager.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    boolean existsByCpf(String cpf);
}
