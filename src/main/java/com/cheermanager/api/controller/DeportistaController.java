package com.cheermanager.api.controller;

import com.cheermanager.api.dto.DeportistaRequest;
import com.cheermanager.api.model.Deportista;
import com.cheermanager.api.service.DeportistaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/deportistas")
public class DeportistaController {

    private final DeportistaService deportistaService;

    // Inyección del servicio mediante constructor
    public DeportistaController(DeportistaService deportistaService) {
        this.deportistaService = deportistaService;
    }

    // 1. Obtener todos los deportistas
    @GetMapping
    public ResponseEntity<List<Deportista>> obtenerDeportistas() {

        return ResponseEntity.ok(
                deportistaService.obtenerTodos()
        );
    }

    // 2. Obtener un deportista por ID
    @GetMapping("/{id}")
    public ResponseEntity<Deportista> obtenerDeportistaPorId(
            @PathVariable Long id) {

        Deportista deportista = deportistaService.obtenerPorId(id);

        if (deportista == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deportista);
    }

    // 3. Buscar deportistas por categoría
    @GetMapping("/buscar")
    public ResponseEntity<List<Deportista>> buscarPorCategoria(
            @RequestParam String categoria) {

        return ResponseEntity.ok(
                deportistaService.buscarPorCategoria(categoria)
        );
    }

    // 4. Registrar un nuevo deportista
    @PostMapping
    public ResponseEntity<Deportista> registrarDeportista(
            @RequestBody DeportistaRequest request) {

        Deportista nuevoDeportista =
                deportistaService.registrar(request);

        return ResponseEntity
                .status(201)
                .body(nuevoDeportista);
    }
}