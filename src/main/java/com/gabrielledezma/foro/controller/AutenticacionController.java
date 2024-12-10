package com.gabrielledezma.foro.controller;

import com.gabrielledezma.foro.domain.DTO.usuario.DatosAutenticacionUsuario;

import com.gabrielledezma.foro.domain.model.Usuario;
import jakarta.validation.Valid;
import com.gabrielledezma.foro.infra.security.DatosTokenJWT;
import com.gabrielledezma.foro.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos){
        var authToken = new UsernamePasswordAuthenticationToken(datos.nombre(),datos.contrasenia());
        var usuarioAutenticado = manager.authenticate(authToken);
        var tokenJWT = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}
