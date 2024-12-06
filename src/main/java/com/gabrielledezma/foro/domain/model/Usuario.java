package com.gabrielledezma.foro.domain.model;

import com.gabrielledezma.foro.domain.DTO.usuario.DatosActualizarUsuario;
import com.gabrielledezma.foro.domain.DTO.usuario.DatosRegistroUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="usuarios")
@Entity(name="Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasenia;
    private Boolean activo;

    public Usuario(DatosRegistroUsuario datos){
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.contrasenia = datos.contrasenia();
        this.activo = true;
    }

    public void darDeBaja(){
        this.activo = false;
    }

    public void darDeAlta(){
        this.activo = true;
    }

    public void actualizarDatos(DatosActualizarUsuario datos) {
        if(datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if(datos.email() != null){
            this.email = datos.email();
        }
        if(datos.contrasenia() != null && datos.contrasenia() == this.contrasenia){
            this.contrasenia = datos.nuevaContrasenia();
        }
    }
}
