package com.gabrielledezma.foro.domain.DTO.topico;

import com.gabrielledezma.foro.domain.model.Topico;

import java.time.LocalDate;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaCreacion) {

    public DatosRespuestaTopico (Topico t){
        this(t.getId(), t.getTitulo(), t.getMensaje(), t.getFechaCreacion());
    }
}
