package com.gabrielledezma.foro.domain.model;

import com.gabrielledezma.foro.domain.DTO.usuario.DatosActualizarUsuario;
import com.gabrielledezma.foro.domain.DTO.usuario.DatosRegistroUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Table(name="usuarios")
@Entity(name="Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasenia;
    private Boolean activo;
    private Rol role;

    public Usuario(DatosRegistroUsuario datos, String contrasenia) {
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.contrasenia = contrasenia;
        this.activo = true;
        this.role = Rol.USER;
    }

    public void darDeBaja(){
        this.activo = false;
    }

    public void darDeAlta(){
        this.activo = true;
    }

    public void actualizarDatos(DatosActualizarUsuario datos, PasswordEncoder passwordEncoder) {
        if(datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if(datos.email() != null){
            this.email = datos.email();
        }
        boolean mismaContra = passwordEncoder.matches(datos.contrasenia(), this.contrasenia);
        if(datos.contrasenia() != null && mismaContra){
            this.contrasenia = passwordEncoder.encode(datos.nuevaContrasenia());
        }
    }

    public void cambiarRol(){
        if(this.role == Rol.ADMIN){
            this.role = Rol.USER;
        } else {
            this.role = Rol.ADMIN;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role == Rol.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if(role == Rol.USER){
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return null;
    }

    @Override
    public String getPassword() {
        return this.contrasenia;
    }

    @Override
    public String getUsername() {
        return this.nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
