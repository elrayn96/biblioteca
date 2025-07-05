package com.armando.biblioteca.repository;

import com.armando.biblioteca.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends  JpaRepository<Usuario, String> {
    boolean existsById(String id);
}