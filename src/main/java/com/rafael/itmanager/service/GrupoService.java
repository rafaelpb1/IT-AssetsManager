package com.rafael.itmanager.service;

import com.rafael.itmanager.model.Grupo;
import com.rafael.itmanager.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class GrupoService {

    private final GrupoRepository repository;


    @Transactional
    public Grupo salvar(Grupo grupo) {
        return repository.save(grupo);
    }

    @Transactional
    public void excluir(String id) {
        repository.deleteById(id);
    }
}
