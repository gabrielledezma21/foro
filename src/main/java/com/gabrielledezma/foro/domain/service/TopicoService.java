package com.gabrielledezma.foro.domain.service;

import com.gabrielledezma.foro.domain.DTO.topico.*;
import com.gabrielledezma.foro.domain.excepciones.ValidacionException;
import com.gabrielledezma.foro.domain.model.Curso;
import com.gabrielledezma.foro.domain.model.Topico;
import com.gabrielledezma.foro.domain.model.Usuario;
import com.gabrielledezma.foro.domain.repository.CursoRepository;
import com.gabrielledezma.foro.domain.repository.TopicoRepository;
import com.gabrielledezma.foro.domain.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    //@Autowired
    //private List<ValidadorDeRegistroTopicos> validadoresDeRegistro;

    //@Autowired
    //private List<ValidadorDeEliminacionTopicos> validadoresDeEliminacion;

    public DatosRespuestaTopico registrar(DatosRegistroTopico datos){
        if(!usuarioRepository.findActivoById(datos.idUsuario())){
            throw new ValidacionException("No existe ese usuario o se encuentra dado de baja");
        }
        if(!cursoRepository.findActivoById(datos.idCurso())){
            throw new ValidacionException("No existe ese curso o se encuentra dado de baja");
        }

        //validadoresDeRegistro.forEach(v -> v.validar(datos));

        Usuario u = usuarioRepository.findById(datos.idUsuario()).get();
        Curso c = cursoRepository.findById(datos.idCurso()).get();
        Topico t = new Topico(datos, u, c);
        topicoRepository.save(t);
        return new DatosRespuestaTopico(t);
    }

    public Page<DatosListadoActivosTopico> listar(Pageable paginacion) {
        return topicoRepository.findByEstadoTrue(paginacion)
                .map(DatosListadoActivosTopico::new);
    }

    public Page<DatosListadoTopico> listarTodos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion)
                .map(DatosListadoTopico::new);
    }

    @Transactional
    public DatosRespuestaTopico actualizar(@Valid DatosActualizarTopico datos) {
        Topico t = topicoRepository.getReferenceById(datos.id());
        t.actualizarDatos(datos);
        return new DatosRespuestaTopico(t);
    }

    @Transactional
    public void eliminar(Long id) {
        Topico t = topicoRepository.getReferenceById(id);
        t.darDeBaja();
    }

    @Transactional
    public DatosRespuestaTopico reActivar(Long id) {
        Topico t = topicoRepository.getReferenceById(id);
        t.darDeAlta();
        return new DatosRespuestaTopico(t);
    }

    public DatosListadoActivosTopico verTopico(Long id) {
        Topico t = topicoRepository.getReferenceById(id);
        return new DatosListadoActivosTopico(t);
    }
}
