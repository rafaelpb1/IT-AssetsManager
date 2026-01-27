package com.rafael.itmanager.service;

import com.rafael.itmanager.dto.EmprestimoRequestDTO;
import com.rafael.itmanager.dto.EmprestimoResponseDTO;
import com.rafael.itmanager.mapper.EmprestimoMapper;
import com.rafael.itmanager.model.Emprestimo;
import com.rafael.itmanager.model.StatusEquipamento;
import com.rafael.itmanager.repository.ColaboradorRepository;
import com.rafael.itmanager.repository.EmprestimoRepository;
import com.rafael.itmanager.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final EquipamentoRepository equipamentoRepository;
    private final EmprestimoMapper mapper;

    @Transactional
    public EmprestimoResponseDTO realizarEmprestimo(EmprestimoRequestDTO dto) {
        var colaborador = colaboradorRepository.findById(dto.colaboradorId())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Colaborador não encontrado"));

        var equipamento = equipamentoRepository.findById(dto.equipamentoId())
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado"));

        if(equipamento.getStatus() != StatusEquipamento.DISPONIVEL) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este equipamento não está disponível para empréstimo");
        }

        Emprestimo emprestimo = mapper.toEntity(dto);
        emprestimo.setColaborador(colaborador);
        emprestimo.setEquipamento(equipamento);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(dto.dataDevolucao());
        emprestimo.setObservacao(dto.observacao());

        equipamento.setStatus(StatusEquipamento.EMPRESTADO);
        Emprestimo salvo = emprestimoRepository.save(emprestimo);
        return mapper.toDTO(salvo);
    }

    public List<EmprestimoResponseDTO> listarEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        List<EmprestimoResponseDTO> lista = emprestimos.stream()
                .map(mapper::toDTO)
                .toList();

        if(emprestimos.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Não há nenhum empréstimo registrado");

        return lista;
    }

    public EmprestimoResponseDTO buscarEmprestimoPorId(Long id) {
        var emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empréstimo não encontrado"));
        return new EmprestimoResponseDTO(emprestimo);
    }

    @Transactional
    public EmprestimoResponseDTO atualizarEmprestimo(Long id, EmprestimoRequestDTO dto) {
        var emprestimo = emprestimoRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empréstimo não encontrado"));

        mapper.updateFromDTO(dto, emprestimo);
        Emprestimo atualizado = emprestimoRepository.save(emprestimo);
        return mapper.toDTO(atualizado);
    }

    @Transactional
    public void excluirEmprestimo(Long id) {
        var emprestimo = emprestimoRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empréstimo não encontrado"));

        var equipamento = emprestimo.getEquipamento();
        equipamento.setStatus(StatusEquipamento.DISPONIVEL);

        equipamentoRepository.save(equipamento);
        emprestimoRepository.delete(emprestimo);
    }
}
