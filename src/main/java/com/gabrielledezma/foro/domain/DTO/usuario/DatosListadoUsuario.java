package com.gabrielledezma.foro.domain.DTO.usuario;

import com.gabrielledezma.foro.domain.model.Usuario;

public record DatosListadoUsuario(Long id,
                                  String nombre,
                                  String email,
                                  Boolean activo) {
    public DatosListadoUsuario(Usuario u) {
        this(u.getId(), u.getNombre(), u.getEmail(), u.getActivo());
    }
}
