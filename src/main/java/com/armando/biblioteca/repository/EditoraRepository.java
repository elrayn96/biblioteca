package com.armando.biblioteca.repository;

import com.armando.biblioteca.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends  JpaRepository<Editora, String> {
    boolean existsById(String id);
}