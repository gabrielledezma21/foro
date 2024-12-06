package com.gabrielledezma.foro.controller;

import com.gabrielledezma.foro.domain.DTO.usuario.DatosActualizarUsuario;
import com.gabrielledezma.foro.domain.DTO.usuario.DatosListadoUsuario;
import com.gabrielledezma.foro.domain.DTO.usuario.DatosRegistroUsuario;
import com.gabrielledezma.foro.domain.DTO.usuario.DatosRespuestaUsuario;
import com.gabrielledezma.foro.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrar(@RequestBody @Valid DatosRegistroUsuario datos,
                                                                 UriComponentsBuilder uriComponentsBuilder){
        var respuestaUsuario = usuarioService.registrar(datos);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(respuestaUsuario.id()).toUri();
        return ResponseEntity.created(url).body(respuestaUsuario);
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Page<DatosListadoUsuario>> listar(@PageableDefault(size = 2, sort = "nombre") Pageable paginacion){
        var respuesta = usuarioService.listar(paginacion);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping
    public ResponseEntity<DatosRespuestaUsuario> actualizar(@RequestBody @Valid DatosActualizarUsuario datos) {
        var respuesta = usuarioService.actualizar(datos);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity reActivar(@PathVariable Long id) {
        usuarioService.reActivar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoUsuario> verUsuario(@PathVariable Long id) {
        var respuesta = usuarioService.verUsuario(id);
        return ResponseEntity.ok(respuesta);
    }

}
