package com.gabrielledezma.foro.controller;

import com.gabrielledezma.foro.domain.DTO.curso.*;
import com.gabrielledezma.foro.domain.service.CursoService;
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
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrar(@RequestBody @Valid DatosRegistroCurso datos,
                                                           UriComponentsBuilder uriComponentsBuilder){
        var respuestaCurso = cursoService.registrar(datos);
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(respuestaCurso.id()).toUri();
        return ResponseEntity.created(url).body(respuestaCurso);
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<DatosListadoCurso>> listar(@PageableDefault(size = 2, sort = "nombre") Pageable paginacion){
        var respuesta = cursoService.listar(paginacion);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DatosRespuestaCurso> actualizar(@RequestBody @Valid DatosActualizarCurso datos) {
        var respuesta = cursoService.actualizar(datos);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity reActivar(@PathVariable Long id) {
        cursoService.reActivar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DatosListadoCurso> verUsuario(@PathVariable Long id) {
        var respuesta = cursoService.verUsuario(id);
        return ResponseEntity.ok(respuesta);
    }

}
