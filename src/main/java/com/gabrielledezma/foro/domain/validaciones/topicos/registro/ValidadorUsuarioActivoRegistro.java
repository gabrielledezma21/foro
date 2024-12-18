package com.gabrielledezma.foro.domain.validaciones.topicos.registro;

import com.gabrielledezma.foro.domain.DTO.topico.DatosRegistroTopico;
import com.gabrielledezma.foro.domain.excepciones.ValidacionException;
import com.gabrielledezma.foro.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUsuarioActivoRegistro implements ValidadorDeRegistroTopicos {

    @Autowired
    private UsuarioRepository repository;

    public void validar(DatosRegistroTopico datos) {
        var usuarioEstaActivo = repository.findActivoById(datos.idUsuario());
        if(!usuarioEstaActivo) {
            throw new ValidacionException("El tópico no puede ser creado por un usuario no registrado o dado de baja");
        }
    }
}
