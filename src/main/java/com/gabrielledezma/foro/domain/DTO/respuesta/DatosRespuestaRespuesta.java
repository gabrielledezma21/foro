package com.gabrielledezma.foro.domain.DTO.respuesta;

import com.gabrielledezma.foro.domain.model.Respuesta;

import java.time.LocalDate;

public record DatosRespuestaRespuesta(
        Long id,
        String mensaje,
        LocalDate fechaCreacion) {

    public DatosRespuestaRespuesta (Respuesta r){
        this(r.getId(), r.getMensaje(), r.getFechaCreacion());
    }
}
