package com.gabrielledezma.foro.domain.DTO.curso;

import com.gabrielledezma.foro.domain.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroCurso(
        @NotBlank
        String nombre,
        @NotNull
        Categoria categoria) {
}
