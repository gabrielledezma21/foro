package com.gabrielledezma.foro.domain.model;

import com.gabrielledezma.foro.domain.DTO.respuesta.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name="respuestas")
@Entity(name="Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDate fechaCreacion;
    private Boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Respuesta(DatosRegistroRespuesta datos, Topico topico, Usuario usuario) {
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDate.now();
        this.activo = true;
        this.topico = topico;
        this.usuario = usuario;
    }

    public void darDeBaja(){
        this.activo = false;
    }

    public void darDeAlta(){
        this.activo = true;
    }

    public void actualizarDatos(DatosActualizarRespuesta datos) {
        boolean seActualizo = false;
        if(datos.mensaje() != null){
            this.mensaje = datos.mensaje();
            seActualizo = true;
        }
        if (seActualizo) {
            this.fechaCreacion = LocalDate.now();
        }
    }
}
