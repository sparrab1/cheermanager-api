package com.cheermanager.api.service;

import com.cheermanager.api.dto.DeportistaRequest;
import com.cheermanager.api.model.Deportista;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeportistaService {

    private final List<Deportista> deportistas = new ArrayList<>();

    private Long siguienteId = 1L;

    public DeportistaService() {

        deportistas.add(new Deportista(
                siguienteId++,
                "Valentina",
                "Gómez",
                13,
                "Junior",
                "Nivel 2",
                "Flyer",
                "ACTIVO"
        ));

        deportistas.add(new Deportista(
                siguienteId++,
                "Mariana",
                "Rodríguez",
                15,
                "Junior",
                "Nivel 3",
                "Base",
                "ACTIVO"
        ));

        deportistas.add(new Deportista(
                siguienteId++,
                "Sofía",
                "Martínez",
                14,
                "Juvenil",
                "Nivel 2",
                "Back Spot",
                "ACTIVO"
        ));
    }

    // Obtener todos los deportistas
    public List<Deportista> obtenerTodos() {
        return deportistas;
    }

    // Obtener un deportista por ID
    public Deportista obtenerPorId(Long id) {

        return deportistas.stream()
                .filter(deportista -> deportista.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Buscar deportistas por categoría
    public List<Deportista> buscarPorCategoria(String categoria) {

        return deportistas.stream()
                .filter(deportista ->
                        deportista.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    // Registrar un nuevo deportista
    public Deportista registrar(DeportistaRequest request) {

        Deportista nuevoDeportista = new Deportista(
                siguienteId++,
                request.nombre(),
                request.apellido(),
                request.edad(),
                request.categoria(),
                request.nivel(),
                request.posicion(),
                "ACTIVO"
        );

        deportistas.add(nuevoDeportista);

        return nuevoDeportista;
    }
}