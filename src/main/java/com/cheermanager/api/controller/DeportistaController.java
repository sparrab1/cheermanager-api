package com.cheermanager.api.controller;

import com.cheermanager.api.dto.DeportistaRequest;
import com.cheermanager.api.model.Deportista;
import com.cheermanager.api.service.DeportistaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/deportistas")
public class DeportistaController {

    private final DeportistaService deportistaService;

    public DeportistaController(DeportistaService deportistaService) {
        this.deportistaService = deportistaService;
    }

    // GET - Obtener todos
    @GetMapping
    public ResponseEntity<List<Deportista>> obtenerDeportistas() {
        return ResponseEntity.ok(
                deportistaService.obtenerTodos()
        );
    }

    // GET - Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Deportista> obtenerDeportistaPorId(
            @PathVariable Long id) {

        Deportista deportista = deportistaService.obtenerPorId(id);

        if (deportista == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deportista);
    }

    // GET - Buscar por categoría
    @GetMapping("/buscar")
    public ResponseEntity<List<Deportista>> buscarPorCategoria(
            @RequestParam String categoria) {

        return ResponseEntity.ok(
                deportistaService.buscarPorCategoria(categoria)
        );
    }

    // POST - Registrar
    @PostMapping
    public ResponseEntity<Deportista> registrarDeportista(
            @RequestBody DeportistaRequest request) {

        Deportista nuevoDeportista =
                deportistaService.registrar(request);

        return ResponseEntity
                .status(201)
                .body(nuevoDeportista);
    }

    // PUT - Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Deportista> actualizarDeportista(
            @PathVariable Long id,
            @RequestBody DeportistaRequest request) {

        Deportista deportistaActualizado =
                deportistaService.actualizar(id, request);

        if (deportistaActualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deportistaActualizado);
    }

    // DELETE - Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDeportista(
            @PathVariable Long id) {

        boolean eliminado = deportistaService.eliminar(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}