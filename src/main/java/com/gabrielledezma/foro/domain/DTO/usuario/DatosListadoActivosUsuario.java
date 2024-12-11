package com.gabrielledezma.foro.domain.DTO.usuario;

import com.gabrielledezma.foro.domain.model.Usuario;

public record DatosListadoActivosUsuario(Long id,
                                         String nombre,
                                         String email) {
    public DatosListadoActivosUsuario(Usuario u) {
        this(u.getId(), u.getNombre(), u.getEmail());
    }
}
