package com.gabrielledezma.foro.domain.service;



import com.gabrielledezma.foro.domain.DTO.curso.DatosActualizarCurso;
import com.gabrielledezma.foro.domain.DTO.curso.DatosListadoCurso;
import com.gabrielledezma.foro.domain.DTO.curso.DatosRegistroCurso;
import com.gabrielledezma.foro.domain.DTO.curso.DatosRespuestaCurso;
import com.gabrielledezma.foro.domain.model.Curso;
import com.gabrielledezma.foro.domain.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public DatosRespuestaCurso registrar(DatosRegistroCurso datos) {
        Curso c = new Curso(datos);
        cursoRepository.save(c);
        return new DatosRespuestaCurso(c);
    }

    public Page<DatosListadoCurso> listar(Pageable paginacion){
        return cursoRepository.findAll(paginacion)
                .map(DatosListadoCurso::new);
    }

    @Transactional
    public DatosRespuestaCurso actualizar(DatosActualizarCurso datos){
        Curso c = cursoRepository.getReferenceById(datos.id());
        c.actualizarDatos(datos);
        return new DatosRespuestaCurso(c);
    }

    @Transactional
    public void eliminar(Long id){
        Curso c = cursoRepository.getReferenceById(id);
        c.darDeBaja();
    }

    @Transactional
    public void reActivar(Long id){
        Curso c = cursoRepository.getReferenceById(id);
        c.darDeAlta();
    }

    public DatosListadoCurso verUsuario(Long id){
        Curso c = cursoRepository.getReferenceById(id);
        return new DatosListadoCurso(c);
    }

}
