package com.armando.biblioteca.repository;

import com.armando.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LivroRepository extends  JpaRepository<Livro, String> {
    List<Livro> findByAutorId(String autorId);
    boolean existsById(String id);
}