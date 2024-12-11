package com.gabrielledezma.foro.domain.DTO.curso;

import com.gabrielledezma.foro.domain.model.Categoria;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(
        @NotNull
        Long id,
        String nombre,
        Categoria categoria
) {
}
