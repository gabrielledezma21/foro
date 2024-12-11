package com.gabrielledezma.foro.domain.repository;

import com.gabrielledezma.foro.domain.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByEstadoTrue(Pageable paginacion);
}
