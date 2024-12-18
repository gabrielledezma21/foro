package com.gabrielledezma.foro.domain.validaciones.topicos.actualizado;

import com.gabrielledezma.foro.domain.DTO.topico.DatosActualizarTopico;
import com.gabrielledezma.foro.domain.excepciones.ValidacionException;
import com.gabrielledezma.foro.domain.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTopicoMismoTituloYMensajeActualizado implements ValidadorDeActualizadoTopicos{

    @Autowired
    private TopicoRepository repository;

    public void validar(DatosActualizarTopico datos) {
        var titulo = datos.titulo();
        var mensaje = datos.mensaje();
        var tituloYMensajeGuardados = repository.existsByTituloAndMensaje(titulo, mensaje);
        if(tituloYMensajeGuardados){
            throw new ValidacionException("No puede haber dos topicos con el mismo titulo y mensaje.");
        }
    }
}
