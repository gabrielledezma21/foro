package com.gabrielledezma.foro.controller;

import com.gabrielledezma.foro.domain.DTO.topico.*;
import com.gabrielledezma.foro.domain.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrar(@RequestBody @Valid DatosRegistroTopico datos,
                                                         UriComponentsBuilder uriComponentsBuilder){
        var respuestaTopico = topicoService.registrar(datos);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(respuestaTopico.id()).toUri();
        return ResponseEntity.created(url).body(respuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoActivosTopico>> listar(@PageableDefault(size = 5, sort = "id") Pageable paginacion){
        var respuesta = topicoService.listar(paginacion);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/listarTodos")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Page<DatosListadoTopico>> listarTodos(@PageableDefault(size = 5, sort = "id") Pageable paginacion){
        var respuesta = topicoService.listarTodos(paginacion);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping
    public ResponseEntity<DatosRespuestaTopico> actualizar(@RequestBody @Valid DatosActualizarTopico datos) {
        var respuesta = topicoService.actualizar(datos);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity eliminar(@PathVariable Long id) {
        topicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reActivar/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<DatosRespuestaTopico> reActivar(@PathVariable Long id) {
        var respuesta = topicoService.reActivar(id);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoActivosTopico> verUsuario(@PathVariable Long id) {
        var respuesta = topicoService.verTopico(id);
        return ResponseEntity.ok(respuesta);
    }

}
