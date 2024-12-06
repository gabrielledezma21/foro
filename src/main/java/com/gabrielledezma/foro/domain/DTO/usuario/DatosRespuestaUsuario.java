package com.gabrielledezma.foro.domain.DTO.usuario;

import com.gabrielledezma.foro.domain.model.Usuario;

public record DatosRespuestaUsuario(Long id,
                                    String nombre,
                                    String email) {
    public DatosRespuestaUsuario (Usuario u){
        this(u.getId(), u.getNombre(), u.getEmail());
    }
}
