package com.armando.biblioteca.repository;

import com.armando.biblioteca.model.AreaConhecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaConhecimentoRepository extends  JpaRepository<AreaConhecimento, String> {
    boolean existsById(String id);
}