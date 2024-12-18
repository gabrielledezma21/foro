package com.gabrielledezma.foro.domain.service;

import com.gabrielledezma.foro.domain.DTO.topico.*;
import com.gabrielledezma.foro.domain.model.Curso;
import com.gabrielledezma.foro.domain.model.Topico;
import com.gabrielledezma.foro.domain.model.Usuario;
import com.gabrielledezma.foro.domain.repository.CursoRepository;
import com.gabrielledezma.foro.domain.repository.TopicoRepository;
import com.gabrielledezma.foro.domain.repository.UsuarioRepository;
import com.gabrielledezma.foro.domain.validaciones.topicos.actualizado.ValidadorDeActualizadoTopicos;
import com.gabrielledezma.foro.domain.validaciones.topicos.eliminado.ValidadorDeEliminadoTopicos;
import com.gabrielledezma.foro.domain.validaciones.topicos.listado.ValidadorDeListadoTopicos;
import com.gabrielledezma.foro.domain.validaciones.topicos.registro.ValidadorDeRegistroTopicos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private List<ValidadorDeRegistroTopicos> validadoresDeRegistro;

    @Autowired
    private List<ValidadorDeListadoTopicos> validadoresDeListado;

    @Autowired
    private List<ValidadorDeActualizadoTopicos> validadoresDeActualizado;

    @Autowired
    private List<ValidadorDeEliminadoTopicos> validadoresDeEliminado;

    public DatosRespuestaTopico registrar(DatosRegistroTopico datos){
        validadoresDeRegistro.forEach(v -> v.validar(datos));

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
        validadoresDeActualizado.forEach(v -> v.validar(datos));
        Topico t = topicoRepository.getReferenceById(datos.id());
        t.actualizarDatos(datos);
        return new DatosRespuestaTopico(t);
    }

    @Transactional
    public void eliminar(Long id) {
        validadoresDeEliminado.forEach(v -> v.validar(id));
        Topico t = topicoRepository.getReferenceById(id);
        t.darDeBaja();
    }

    @Transactional
    public DatosRespuestaTopico reActivar(Long id) {
        Topico t = topicoRepository.getReferenceById(id);
        t.darDeAlta();
        return new DatosRespuestaTopico(t);
    }

    public DatosListadoTopico verTopico(Long id) {
        validadoresDeListado.forEach(v -> v.validar(id));
        Topico t = topicoRepository.getReferenceById(id);
        return new DatosListadoTopico(t);
    }

    public Page<DatosListadoTopico> listarTodosPorNombreDeCursoYAnio(Pageable paginacion, String nombre, String anio) {
        Curso c = cursoRepository.findByNombre(nombre);
        LocalDate fechaInicio = LocalDate.of(Integer.parseInt(anio), 1, 1);
        LocalDate fechaFin = LocalDate.of(Integer.parseInt(anio), 12, 31);
        return topicoRepository.findByCurso_idAndFechaCreacionBetween(c.getId(), fechaInicio, fechaFin, paginacion)
                .map(DatosListadoTopico::new);
    }
}
