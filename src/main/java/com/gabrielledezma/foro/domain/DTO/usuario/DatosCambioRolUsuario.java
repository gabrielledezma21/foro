package com.gabrielledezma.foro.domain.DTO.usuario;

import com.gabrielledezma.foro.domain.model.Usuario;

public record DatosCambioRolUsuario(Long id,
                                    String nombre,
                                    String email,
                                    String rol) {
    public DatosCambioRolUsuario (Usuario u){
        this(u.getId(), u.getNombre(), u.getEmail(), u.getRole().toString());
    }
}
