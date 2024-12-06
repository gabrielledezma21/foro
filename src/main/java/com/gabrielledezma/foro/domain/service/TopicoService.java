package com.gabrielledezma.foro.domain.service;

import com.gabrielledezma.foro.domain.DTO.topico.DatosRegistroTopico;
import com.gabrielledezma.foro.domain.DTO.topico.DatosRespuestaTopico;
import com.gabrielledezma.foro.domain.excepciones.ValidacionException;
import com.gabrielledezma.foro.domain.model.Usuario;
import com.gabrielledezma.foro.domain.repository.CursoRepository;
import com.gabrielledezma.foro.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    //@Autowired
    //private List<ValidadorDeRegistroTopicos> validadoresDeRegistro;

    //@Autowired
    //private List<ValidadorDeEliminacionTopicos> validadoresDeEliminacion;

    public DatosRespuestaTopico registrar(DatosRegistroTopico datos){

        if(!usuarioRepository.findActivoById(datos.usuario().getId())){
            throw new ValidacionException("No existe ese usuario o se encuentra dado de baja");
        }

        if(!cursoRepository.findActivoById(datos.curso().getId())){
            throw new ValidacionException("No existe ese curso o se encuentra dado de baja");
        }

        //validadoresDeRegistro.forEach(v -> v.validar(datos));

        Usuario usuario = usuarioRepository.findById(datos.usuario().getId()).get();

        return null;

    }
}
