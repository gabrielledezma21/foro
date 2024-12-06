package com.gabrielledezma.foro.domain.DTO.topico;


import com.gabrielledezma.foro.domain.model.Curso;
import com.gabrielledezma.foro.domain.model.Usuario;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        Usuario usuario,
        Curso curso) {



}
