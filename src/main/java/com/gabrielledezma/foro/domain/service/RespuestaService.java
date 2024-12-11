package com.gabrielledezma.foro.domain.service;

import com.gabrielledezma.foro.domain.DTO.respuesta.*;
import com.gabrielledezma.foro.domain.excepciones.ValidacionException;
import com.gabrielledezma.foro.domain.model.Respuesta;
import com.gabrielledezma.foro.domain.model.Topico;
import com.gabrielledezma.foro.domain.model.Usuario;
import com.gabrielledezma.foro.domain.repository.RespuestaRepository;
import com.gabrielledezma.foro.domain.repository.TopicoRepository;
import com.gabrielledezma.foro.domain.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RespuestaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    //@Autowired
    //private List<ValidadorDeRegistroTopicos> validadoresDeRegistro;

    //@Autowired
    //private List<ValidadorDeEliminacionTopicos> validadoresDeEliminacion;

    public DatosRespuestaRespuesta registrar(DatosRegistroRespuesta datos){
        if(!usuarioRepository.findActivoById(datos.idUsuario())){
            throw new ValidacionException("No existe ese usuario o se encuentra dado de baja");
        }
        if(!topicoRepository.findEstadoById(datos.idTopico())){
            throw new ValidacionException("No existe ese tópico o se encuentra dado de baja");
        }

        //validadoresDeRegistro.forEach(v -> v.validar(datos));

        Usuario u = usuarioRepository.findById(datos.idUsuario()).get();
        Topico t = topicoRepository.findById(datos.idTopico()).get();
        Respuesta r = new Respuesta(datos, t, u);
        respuestaRepository.save(r);
        return new DatosRespuestaRespuesta(r);
    }

    public Page<DatosListadoActivosRespuesta> listar(Pageable paginacion) {
        return respuestaRepository.findByActivoTrue(paginacion)
                .map(DatosListadoActivosRespuesta::new);
    }

    public Page<DatosListadoRespuesta> listarTodos(Pageable paginacion) {
        return respuestaRepository.findAll(paginacion)
                .map(DatosListadoRespuesta::new);
    }

    @Transactional
    public DatosRespuestaRespuesta actualizar(@Valid DatosActualizarRespuesta datos) {
        Respuesta r = respuestaRepository.getReferenceById(datos.id());
        r.actualizarDatos(datos);
        return new DatosRespuestaRespuesta(r);
    }

    @Transactional
    public void eliminar(Long id) {
        Respuesta r = respuestaRepository.getReferenceById(id);
        r.darDeBaja();
    }

    @Transactional
    public DatosRespuestaRespuesta reActivar(Long id) {
        Respuesta r = respuestaRepository.getReferenceById(id);
        r.darDeAlta();
        return new DatosRespuestaRespuesta(r);
    }

    public DatosListadoActivosRespuesta verRespuesta(Long id) {
        Respuesta r = respuestaRepository.getReferenceById(id);
        return new DatosListadoActivosRespuesta(r);
    }
}
