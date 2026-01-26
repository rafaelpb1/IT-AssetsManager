package com.rafael.itmanager.repository;

import com.rafael.itmanager.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    @Query(" SELECT e FROM Emprestimo e WHERE e.dataDevolucao < CURRENT_DATE")
    List<Emprestimo> findEmprestimosAtrasados();

    boolean existsByColaboradorId(Long id);
}
