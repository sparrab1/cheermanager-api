package com.cheermanager.api.service;

import com.cheermanager.api.dto.DeportistaRequest;
import com.cheermanager.api.model.Deportista;
import com.cheermanager.api.repository.DeportistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeportistaService {

    private final DeportistaRepository deportistaRepository;

    public DeportistaService(DeportistaRepository deportistaRepository) {
        this.deportistaRepository = deportistaRepository;
    }

    // Obtener todos los deportistas
    public List<Deportista> obtenerTodos() {
        return deportistaRepository.findAll();
    }

    // Obtener deportista por ID
    public Deportista obtenerPorId(Long id) {
        return deportistaRepository.findById(id).orElse(null);
    }

    // Buscar deportistas por categoría
    public List<Deportista> buscarPorCategoria(String categoria) {
        return deportistaRepository.findByCategoriaIgnoreCase(categoria);
    }

    // Registrar un nuevo deportista
    public Deportista registrar(DeportistaRequest request) {

        Deportista nuevoDeportista = new Deportista(
                request.nombre(),
                request.apellido(),
                request.edad(),
                request.categoria(),
                request.nivel(),
                request.posicion(),
                "ACTIVO"
        );

        return deportistaRepository.save(nuevoDeportista);
    }

    // Actualizar un deportista existente
    public Deportista actualizar(Long id, DeportistaRequest request) {

        Deportista deportista = deportistaRepository.findById(id)
                .orElse(null);

        if (deportista == null) {
            return null;
        }

        deportista.setNombre(request.nombre());
        deportista.setApellido(request.apellido());
        deportista.setEdad(request.edad());
        deportista.setCategoria(request.categoria());
        deportista.setNivel(request.nivel());
        deportista.setPosicion(request.posicion());

        return deportistaRepository.save(deportista);
    }

    // Eliminar un deportista
    public boolean eliminar(Long id) {

        if (!deportistaRepository.existsById(id)) {
            return false;
        }

        deportistaRepository.deleteById(id);
        return true;
    }
}