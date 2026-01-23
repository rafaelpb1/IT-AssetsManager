package com.rafael.itmanager.repository;

import com.rafael.itmanager.model.Emprestimo;
import com.rafael.itmanager.model.Equipamento;
import com.rafael.itmanager.model.StatusEquipamento;
import com.rafael.itmanager.model.TipoEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    @Query(" SELECT e FROM Emprestimo e WHERE e.dataDevolucao < CURRENT_DATE")
    List<Emprestimo> findEmprestimosAtrasados();

    boolean existsByColaboradorId(Long id);
}
