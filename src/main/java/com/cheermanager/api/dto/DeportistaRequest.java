package com.cheermanager.api.dto;

public record DeportistaRequest(
        String nombre,
        String apellido,
        int edad,
        String categoria,
        String nivel,
        String posicion
) {
}
