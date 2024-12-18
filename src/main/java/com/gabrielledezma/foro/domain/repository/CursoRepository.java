package com.gabrielledezma.foro.domain.repository;

import com.gabrielledezma.foro.domain.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("""
            SELECT c.activo
            FROM Curso c
            WHERE c.id = :idCurso
            """)
    boolean findActivoById(Long idCurso);

    Page<Curso> findByActivoTrue(Pageable paginacion);

    Curso findByNombre(String nombre);
}
