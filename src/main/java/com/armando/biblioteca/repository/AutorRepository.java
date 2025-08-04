package com.armando.biblioteca.repository;

import com.armando.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends  JpaRepository<Autor, String> {
    boolean existsById(String id);
}