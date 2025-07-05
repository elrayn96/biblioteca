package com.armando.biblioteca.services;

import com.armando.biblioteca.Validacao;
import com.armando.biblioteca.model.Exemplar;
import com.armando.biblioteca.repository.ExemplarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExemplarService {
    private final ExemplarRepository exemplarRepo;
    private Validacao validar = new Validacao();

    @Autowired
    private LivroService livroService;

    public ExemplarService(ExemplarRepository exemplarRepo) {
        this.exemplarRepo = exemplarRepo;
    }

    // 1. Cadastro de um Exemplar
    public void cadastrarExemplar(Exemplar exemplar, String livroId) {
        List<Exemplar> exemplares = exemplarRepo.findAll();
        if (!exemplarRepo.existsById(exemplar.getId())) {
            exemplar.setEstado("Disponivel");
            if (exemplares.size() == 0) {
                exemplar.setId(validar.validarID("EXE000000"));
            } else {
                exemplar.setId(validar.validarID(exemplares.get(exemplares.size() - 1).getId()));
            }
            exemplar.setLivro(livroService.dadosLivro(livroId).get());
        }
        exemplarRepo.save(exemplar);
    }

    public void cadastrarExemplar(Exemplar exemplar) {
        exemplarRepo.save(exemplar);
    }


    public Optional<Exemplar> dadosExemplar(String id) {
        return exemplarRepo.findById(id);
    }

    // 4. Listar Exemplares
    public List<Exemplar> listarExemplares() {
        return exemplarRepo.findAll();
    }

    public List<Exemplar> buscarExemplarLivroOuId(String query) {
        String q = query.toLowerCase();
        return exemplarRepo.findAll().stream()
        .filter(u ->  u.getEstado().equalsIgnoreCase("Disponivel"))
        .filter(u -> u.getId().toLowerCase().contains(q) 
        ||  u.getLivro().getNome().toLowerCase().contains(q)).collect(Collectors.toList());
    
    }


    public List<Exemplar> listaExemplarId(List<String> ids) {
        return exemplarRepo.findAllById(ids);
    }
}

