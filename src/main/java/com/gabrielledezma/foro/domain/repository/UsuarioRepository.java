package com.gabrielledezma.foro.domain.repository;

import com.gabrielledezma.foro.domain.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("""
            SELECT u.activo
            FROM Usuario u
            WHERE 
            u.id = :idUsuario
            """)
    boolean findActivoById(Long idUsuario);

    Page<Usuario> findByActivoTrue(Pageable paginacion);

    UserDetails findByNombre(String username);
}
