package com.rafael.itmanager.repository;

import com.rafael.itmanager.model.Equipamento;
import com.rafael.itmanager.model.StatusEquipamento;
import com.rafael.itmanager.model.TipoEquipamento;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    List<Equipamento> findByStatus(StatusEquipamento status);

    Optional<Equipamento> findByPatrimonio(String patrimonio);

    List<Equipamento> findByTipoAndStatus(TipoEquipamento tipo, StatusEquipamento status);

    boolean existsByPatrimonio(String patrimonio);
}
