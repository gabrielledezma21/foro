package com.gabrielledezma.foro.domain.DTO.usuario;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
        @NotNull
        Long id,
        String nombre,
        String email,
        String contrasenia,
        String nuevaContrasenia
) {
}
