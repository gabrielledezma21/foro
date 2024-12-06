package com.gabrielledezma.foro.domain.service;

import com.gabrielledezma.foro.domain.DTO.usuario.DatosActualizarUsuario;
import com.gabrielledezma.foro.domain.DTO.usuario.DatosListadoUsuario;
import com.gabrielledezma.foro.domain.DTO.usuario.DatosRegistroUsuario;
import com.gabrielledezma.foro.domain.DTO.usuario.DatosRespuestaUsuario;
import com.gabrielledezma.foro.domain.model.Usuario;
import com.gabrielledezma.foro.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public DatosRespuestaUsuario registrar(DatosRegistroUsuario datos) {
        Usuario u = new Usuario(datos);
        usuarioRepository.save(u);
        return new DatosRespuestaUsuario(u);
    }

    public Page<DatosListadoUsuario> listar(Pageable paginacion){
        return usuarioRepository.findAll(paginacion)
                .map(DatosListadoUsuario::new);
    }

    @Transactional
    public DatosRespuestaUsuario actualizar(DatosActualizarUsuario datos){
        Usuario u = usuarioRepository.getReferenceById(datos.id());
        u.actualizarDatos(datos);
        var respuesta = new DatosRespuestaUsuario(u);
        return respuesta;
    }

    @Transactional
    public void eliminar(Long id){
        Usuario u = usuarioRepository.getReferenceById(id);
        u.darDeBaja();
    }

    @Transactional
    public void reActivar(Long id){
        Usuario u = usuarioRepository.getReferenceById(id);
        u.darDeAlta();
    }

    public DatosListadoUsuario verUsuario(Long id){
        Usuario u = usuarioRepository.getReferenceById(id);
        var respuesta = new DatosListadoUsuario(u);
        return respuesta;
    }
}
