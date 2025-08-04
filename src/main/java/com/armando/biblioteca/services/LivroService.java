package com.armando.biblioteca.services;

import com.armando.biblioteca.Validacao;
import com.armando.biblioteca.model.Autor;
import com.armando.biblioteca.model.Livro;
import com.armando.biblioteca.repository.LivroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class LivroService {

    private final LivroRepository livroRepo;
    private Validacao validar = new Validacao();
    
    @Autowired
    private AutorService autorService;


    public LivroService(LivroRepository livroRepo) {
        this.livroRepo = livroRepo;
    }

    // 1. Cadastro de um usuário
    public void cadastrarLivro(Livro livro, List<String> autoresIds){
        List<Livro> livros = livroRepo.findAll();
        if(livros.size() == 0) {
            livro.setID(validar.validarID("LIV00000"));
        } else {
            livro.setID(validar.validarID(livros.get(livros.size()-1).getId()));
        }
        if (autoresIds != null) {
            List<Autor> autores = autorService.dadosAutores(autoresIds);
            livro.setAutor(autores.toArray(new Autor[autores.size()]));
        } else {
            System.out.println("Livro nao cadastrado");
        }
        if(validar.validarNome(livro.getNome())) {
            livroRepo.save(livro);
        } else {
            System.out.println("Livro nao pode ser cadastrado\nNome nao valido");
        }
    }

    // 2. Buscar Livro Por ID
    public Optional<Livro> dadosLivro(String id) {
        return livroRepo.findById(id);
    }

    // 3. Listar Livros( Ordem de ID's)
    public List<Livro> listarLivros() {
        return livroRepo.findAll().stream()
        .sorted(Comparator.comparing(Livro::getId))
        .collect(Collectors.toList());
    }

    // 4. Listar Livros( Ordem Alfabetica)
    public List<Livro> ordenarLivros() {
        return livroRepo.findAll().stream()
        .sorted(Comparator.comparing(Livro::getNome))
        .collect(Collectors.toList());
    }

    // 5. Buscar Livors por autor
    public List<Livro> livrosPorAutor(String autorId) {
        return livroRepo.findByAutorId(autorId);
    }

    

}
