package com.gabrielledezma.foro.domain.model;

import com.gabrielledezma.foro.domain.DTO.curso.DatosActualizarCurso;
import com.gabrielledezma.foro.domain.DTO.curso.DatosRegistroCurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="cursos")
@Entity(name="Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Boolean activo;

    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Curso(DatosRegistroCurso datos) {
        this.nombre = datos.nombre();
        this.activo = true;
        this.categoria = datos.categoria();
    }

    public void darDeBaja(){
        this.activo = false;
    }

    public void darDeAlta(){
        this.activo = true;
    }

    public void actualizarDatos(DatosActualizarCurso datos) {
        if(datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if(datos.categoria() != null){
            this.categoria = datos.categoria();
        }
    }
}
