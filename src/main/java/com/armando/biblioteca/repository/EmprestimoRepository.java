package com.armando.biblioteca.repository;

import com.armando.biblioteca.model.Emprestimo;
import com.armando.biblioteca.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmprestimoRepository extends  JpaRepository<Emprestimo, String> {
    List<Emprestimo> findByUsuario(Usuario usuario);
    Optional<Emprestimo> findByUsuarioAndEstadoIgnoreCase(Usuario usuario, String estado);
    boolean existsByUsuarioAndEstado(Usuario usuario, String estado);
    boolean existsById(String id);

}