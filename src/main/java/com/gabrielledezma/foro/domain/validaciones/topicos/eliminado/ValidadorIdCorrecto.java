package com.gabrielledezma.foro.domain.validaciones.topicos.eliminado;

import com.gabrielledezma.foro.domain.excepciones.ValidacionException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorIdCorrecto implements ValidadorDeEliminadoTopicos {

    public void validar(Long id) {

        if(id == null) {
            throw new ValidacionException("El id no se ingresó correctamente");
        }

    }
}
