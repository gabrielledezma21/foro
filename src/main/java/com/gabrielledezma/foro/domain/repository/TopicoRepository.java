package com.gabrielledezma.foro.domain.repository;

import com.gabrielledezma.foro.domain.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByEstadoTrue(Pageable paginacion);

    @Query("""
            SELECT t.estado
            FROM Topico t
            WHERE t.id = :idTopico
            """)
    boolean findEstadoById(Long idTopico);
}
