package com.gabrielledezma.foro.controller;

import com.gabrielledezma.foro.domain.DTO.respuesta.*;
import com.gabrielledezma.foro.domain.service.RespuestaService;
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
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> registrar(@RequestBody @Valid DatosRegistroRespuesta datos,
                                                          UriComponentsBuilder uriComponentsBuilder){
        var respuesta = respuestaService.registrar(datos);
        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoActivosRespuesta>> listar(@PageableDefault(size = 5, sort = "id") Pageable paginacion){
        var respuesta = respuestaService.listar(paginacion);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<Page<DatosListadoRespuesta>> listarTodos(@PageableDefault(size = 5, sort = "id") Pageable paginacion){
        var respuesta = respuestaService.listarTodos(paginacion);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping
    public ResponseEntity<DatosRespuestaRespuesta> actualizar(@RequestBody @Valid DatosActualizarRespuesta datos) {
        var respuesta = respuestaService.actualizar(datos);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        respuestaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reActivar/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<DatosRespuestaRespuesta> reActivar(@PathVariable Long id) {
        var respuesta = respuestaService.reActivar(id);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoActivosRespuesta> verUsuario(@PathVariable Long id) {
        var respuesta = respuestaService.verRespuesta(id);
        return ResponseEntity.ok(respuesta);
    }

}
