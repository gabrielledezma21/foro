package com.gabrielledezma.foro.domain.model;

import com.gabrielledezma.foro.domain.DTO.topico.DatosActualizarTopico;
import com.gabrielledezma.foro.domain.DTO.topico.DatosRegistroTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name="topicos")
@Entity(name="Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDate fechaCreacion;
    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topico(DatosRegistroTopico datos, Usuario usuario, Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDate.now();
        this.estado = true;
        this.usuario = usuario;
        this.curso = curso;
    }

    public void darDeBaja(){
        this.estado = false;
    }

    public void darDeAlta(){
        this.estado = true;
    }

    public void actualizarDatos(DatosActualizarTopico datos) {
        boolean seActualizo = false;
        if(datos.titulo() != null){
            this.titulo = datos.titulo();
            seActualizo = true;
        }
        if(datos.mensaje() != null){
            this.mensaje = datos.mensaje();
            seActualizo = true;
        }
        if (seActualizo) {
            this.fechaCreacion = LocalDate.now();
        }
    }

}
