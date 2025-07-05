package com.armando.biblioteca.repository;

import com.armando.biblioteca.model.Exemplar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplarRepository extends  JpaRepository<Exemplar, String> {
    List<Exemplar> findByEstado(String estado);
    //List<Exemplar> findByIdOrLivro(String query);
    boolean existsById(String id); 
}