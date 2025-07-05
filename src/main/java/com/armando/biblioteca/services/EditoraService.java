package com.armando.biblioteca.services;

import com.armando.biblioteca.Validacao;
import com.armando.biblioteca.model.Editora;
import com.armando.biblioteca.repository.EditoraRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;


@Service
public class EditoraService {

    private final EditoraRepository editoraRepo;
    private static Validacao validar = new Validacao();

    public EditoraService(EditoraRepository editoraRepo) {
        this.editoraRepo = editoraRepo;
    }

    // 1. Cadastro de um Editora
    public void cadastrarEditora(Editora editora){
        List<Editora> edi  = editoraRepo.findAll();
        if(!editoraRepo.existsById(editora.getId())) {
            if(edi.size() == 0) {
                editora.setID(validar.validarID("EDI00000"));
            } else {
                editora.setID(validar.validarID(edi.get(edi.size()-1).getId()));
            }
        }
        editoraRepo.save(editora);
    }

    // 2. Buscar Editora Por ID
    public Optional<Editora> dadosEditora(String id) {
        return editoraRepo.findById(id);
    }

    // 3. ListarEditoras( Ordem de ID's)
    public List<Editora> listarEditoras() {
        return editoraRepo.findAll().stream()
        .sorted(Comparator.comparing(Editora::getId))
        .collect(Collectors.toList());
    }

    // 4. Listar Editoras( Ordem Alfabetica)
    public List<Editora> ordenarEditoras() {
        return editoraRepo.findAll().stream()
        .sorted(Comparator.comparing(Editora::getNome))
        .collect(Collectors.toList());
    }
 
    
}
