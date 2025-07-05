package com.armando.biblioteca.services;



import com.armando.biblioteca.Validacao;

import com.armando.biblioteca.model.Autor;

import com.armando.biblioteca.repository.AutorRepository;



import org.springframework.stereotype.Service;



import java.util.List;

import java.util.Optional;

import java.util.Comparator;

import java.util.stream.Collectors;





@Service

public class AutorService {

    private final AutorRepository autorRepo;

    private Validacao validar = new Validacao();





    public AutorService(AutorRepository autorRepo) {

        this.autorRepo = autorRepo;

    }



    // 1. Cadastro de um Autor

    public void cadastrarAutor(Autor autor){

        List<Autor> autores = autorRepo.findAll();

        if(!autorRepo.existsById(autor.getId())) {

            if(autores.size() == 0) {

                autor.setId(validar.validarID("AUT00000"));

            } else {

                autor.setId(validar.validarID(autores.get(autores.size()-1).getId()));

            }

        }

        autorRepo.save(autor);

    }



    // 2. Buscar Autor por ID

    public Optional<Autor> dadosAutor(String id) {

        return autorRepo.findById(id);

    }



    // 3. Listar Autores( Ordem de ID's)

    public List<Autor> listarAutores() {

        return autorRepo.findAll().stream()

        .sorted(Comparator.comparing(Autor::getId))

        .collect(Collectors.toList());

    }



    // 4. Listar Autores( Ordem Alfabetica)

    public List<Autor> ordenarAutores() {

        return autorRepo.findAll().stream()

        .sorted(Comparator.comparing(Autor::getNome))

        .collect(Collectors.toList());

    }





    public List<Autor> dadosAutores(List<String> ids) {

        return autorRepo.findAllById(ids);

    }



}
