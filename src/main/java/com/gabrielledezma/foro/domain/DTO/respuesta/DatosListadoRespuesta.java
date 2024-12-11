package com.gabrielledezma.foro.domain.DTO.respuesta;

import com.gabrielledezma.foro.domain.model.Respuesta;

import java.time.LocalDate;

public record DatosListadoRespuesta(
        Long id,
        String mensaje,
        LocalDate fechaCreacion,
        Long idTopico,
        Long idUsuario) {

    public DatosListadoRespuesta(Respuesta r) {
        this(r.getId(), r.getMensaje(), r.getFechaCreacion(),
                r.getTopico().getId(), r.getUsuario().getId());
    }
}
