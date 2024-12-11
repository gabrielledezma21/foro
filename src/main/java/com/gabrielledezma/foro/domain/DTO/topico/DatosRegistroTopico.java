package com.gabrielledezma.foro.domain.DTO.topico;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Long idUsuario,
        @NotNull
        Long idCurso) {

}
