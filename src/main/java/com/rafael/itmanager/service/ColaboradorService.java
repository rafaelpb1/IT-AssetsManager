package com.rafael.itmanager.service;

import com.rafael.itmanager.dto.ColaboradorRequestDTO;
import com.rafael.itmanager.dto.ColaboradorResponseDTO;
import com.rafael.itmanager.model.Colaborador;
import com.rafael.itmanager.repository.ColaboradorRepository;
import com.rafael.itmanager.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColaboradorService {

    private final ColaboradorRepository repository;
    private final EmprestimoRepository  emprestimoRepository;

    @Transactional
    public ColaboradorResponseDTO salvarColaborador(ColaboradorRequestDTO dto) {
        if (dto.cpf() != null && repository.existsByCpf(dto.cpf())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Já existe um colaborador com esse CPF");
        }

        Colaborador colaborador = new Colaborador();
        colaborador.setNome(dto.nome());
        colaborador.setCpf(dto.cpf());
        colaborador.setSetor(dto.setor());

        return new ColaboradorResponseDTO(repository.save(colaborador));
    }

    public List<ColaboradorResponseDTO> listarColaboradores() {
        List<Colaborador> colaboradores = repository.findAll();
        List<ColaboradorResponseDTO> lista = colaboradores.stream()
                .map(ColaboradorResponseDTO::new)
                .toList();

        if (colaboradores.isEmpty()) throw new ResponseStatusException(
                HttpStatus.NO_CONTENT, "Nenhum colaborador encontrado");
        return lista;
    }

    public ColaboradorResponseDTO buscarColaboradorPorId(Long id) {
        var colaborador = repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Colaborador não encontrado"));

        return new ColaboradorResponseDTO(colaborador);
    }

    @Transactional
    public void excluirColaborador(Long id) {
        var colaborador = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Colaborador não encontrado"));

        if (emprestimoRepository.existsByColaboradorId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível deletar um colaborador que possui empréstimos registrados.");
        }

        repository.delete(colaborador);
    }

    @Transactional
    public ColaboradorResponseDTO atualizarColaborador(Long id, ColaboradorRequestDTO dto) {
        var colaborador = repository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Colaborador não encontrado"));

        colaborador.setNome(dto.nome());
        colaborador.setSetor(dto.setor());

        return new  ColaboradorResponseDTO(colaborador);
    }
}
