package com.gabrielledezma.foro.controller;

import com.gabrielledezma.foro.domain.DTO.topico.DatosRegistroTopico;
import com.gabrielledezma.foro.domain.DTO.topico.DatosRespuestaTopico;
import com.gabrielledezma.foro.domain.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos){
        var respuestaTopico = topicoService.registrar(datos);
        return ResponseEntity.ok(respuestaTopico);

    }
}
