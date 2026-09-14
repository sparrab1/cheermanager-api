package com.cheermanager.api.repository;

import com.cheermanager.api.model.Deportista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeportistaRepository extends JpaRepository<Deportista, Long> {

    List<Deportista> findByCategoriaIgnoreCase(String categoria);
}