package com.gabrielledezma.foro.domain.DTO.curso;

import com.gabrielledezma.foro.domain.model.Curso;

public record DatosListadoCurso(Long id,
                                String nombre,
                                String categoria,
                                Boolean activo) {
    public DatosListadoCurso(Curso c) {
        this(c.getId(), c.getNombre(), c.getCategoria().toString() , c.getActivo());
    }
}
