package com.gabrielledezma.foro.domain.service;

import com.gabrielledezma.foro.domain.DTO.usuario.*;
import com.gabrielledezma.foro.domain.model.Usuario;
import com.gabrielledezma.foro.domain.repository.UsuarioRepository;
import com.gabrielledezma.foro.infra.security.SecurityConfigurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder = new SecurityConfigurations().passwordEncoder();

    @Transactional
    public DatosRespuestaUsuario registrar(DatosRegistroUsuario datos) {
        String contraDecodificada = passwordEncoder.encode(datos.contrasenia());
        Usuario u = new Usuario(datos, contraDecodificada);
        usuarioRepository.save(u);
        return new DatosRespuestaUsuario(u);
    }

    public Page<DatosListadoUsuario> listar(Pageable paginacion){
        return usuarioRepository.findAll(paginacion)
                .map(DatosListadoUsuario::new);
    }

    public Page<DatosListadoActivosUsuario> listarActivos(Pageable paginacion){
        return usuarioRepository.findByActivoTrue(paginacion)
                .map(DatosListadoActivosUsuario::new);
    }

    @Transactional
    public DatosRespuestaUsuario actualizar(DatosActualizarUsuario datos){
        Usuario u = usuarioRepository.getReferenceById(datos.id());
        u.actualizarDatos(datos, passwordEncoder);
        return new DatosRespuestaUsuario(u);
    }

    @Transactional
    public void eliminar(Long id){
        Usuario u = usuarioRepository.getReferenceById(id);
        u.darDeBaja();
    }

    @Transactional
    public DatosRespuestaUsuario reActivar(Long id){
        Usuario u = usuarioRepository.getReferenceById(id);
        u.darDeAlta();
        return new DatosRespuestaUsuario(u);
    }

    public DatosListadoActivosUsuario verUsuario(Long id){
        Usuario u = usuarioRepository.getReferenceById(id);
        return new DatosListadoActivosUsuario(u);
    }

    @Transactional
    public DatosCambioRolUsuario cambiarRol(Long id){
        Usuario u = usuarioRepository.getReferenceById(id);
        u.cambiarRol();
        return new DatosCambioRolUsuario(u);
    }

}
