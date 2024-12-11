package com.gabrielledezma.foro.domain.DTO.curso;

import com.gabrielledezma.foro.domain.model.Curso;

public record DatosRespuestaCurso(Long id,
                                  String nombre,
                                  String categoria) {
    public DatosRespuestaCurso (Curso c){
        this(c.getId(), c.getNombre(), c.getCategoria().toString());
    }
}
