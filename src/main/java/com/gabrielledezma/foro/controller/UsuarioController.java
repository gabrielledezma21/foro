package com.gabrielledezma.foro.controller;

import com.gabrielledezma.foro.domain.DTO.usuario.*;
import com.gabrielledezma.foro.domain.model.Usuario;
import com.gabrielledezma.foro.domain.service.UsuarioService;
import com.gabrielledezma.foro.infra.security.DatosTokenJWT;
import com.gabrielledezma.foro.infra.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos){
        var authToken = new UsernamePasswordAuthenticationToken(datos.nombre(),datos.contrasenia());
        var usuarioAutenticado = manager.authenticate(authToken);
        var tokenJWT = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrar(@RequestBody @Valid DatosRegistroUsuario datos,
                                                                 UriComponentsBuilder uriComponentsBuilder){
        var respuestaUsuario = usuarioService.registrar(datos);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(respuestaUsuario.id()).toUri();
        return ResponseEntity.created(url).body(respuestaUsuario);
    }

    @GetMapping("/listarTodos")
    @Secured("ROLE_ADMIN")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<DatosListadoUsuario>> listar(@PageableDefault(size = 5, sort = "id") Pageable paginacion){
        var respuesta = usuarioService.listar(paginacion);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<DatosListadoActivosUsuario>> listarActivos(@PageableDefault(size = 5, sort = "id") Pageable paginacion){
        var respuesta = usuarioService.listarActivos(paginacion);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DatosRespuestaUsuario> actualizar(@RequestBody @Valid DatosActualizarUsuario datos) {
        var respuesta = usuarioService.actualizar(datos);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reActivar/{id}")
    @Secured("ROLE_ADMIN")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DatosRespuestaUsuario> reActivar(@PathVariable Long id) {
        var respuesta = usuarioService.reActivar(id);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DatosListadoActivosUsuario> verUsuario(@PathVariable Long id) {
        var respuesta = usuarioService.verUsuario(id);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/cambiarRol/{id}")
    @Secured("ROLE_ADMIN")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DatosCambioRolUsuario> cambiarRol(@PathVariable Long id) {
        var respuesta = usuarioService.cambiarRol(id);
        return ResponseEntity.ok(respuesta);
    }

}
