package com.gabrielledezma.foro.domain.DTO.topico;

import com.gabrielledezma.foro.domain.model.Topico;

import java.time.LocalDate;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaCreacion,
        Boolean estado,
        Long idUsuario,
        Long idCurso) {

    public DatosListadoTopico(Topico t) {
        this(t.getId(), t.getTitulo(), t.getMensaje(), t.getFechaCreacion(),
                t.getEstado(), t.getUsuario().getId(), t.getCurso().getId());
    }
}
