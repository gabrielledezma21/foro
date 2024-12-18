package com.gabrielledezma.foro.domain.validaciones.topicos.registro;

import com.gabrielledezma.foro.domain.DTO.topico.DatosRegistroTopico;
import com.gabrielledezma.foro.domain.excepciones.ValidacionException;
import com.gabrielledezma.foro.domain.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCursoActivoRegistro implements ValidadorDeRegistroTopicos {

    @Autowired
    private CursoRepository repository;

    public void validar(DatosRegistroTopico datos) {
        var cursoEstaActivo = repository.findActivoById(datos.idCurso());
        if(!cursoEstaActivo) {
            throw new ValidacionException("El tópico no puede ser creado con un curso no registrado o dado de baja");
        }
    }
}
