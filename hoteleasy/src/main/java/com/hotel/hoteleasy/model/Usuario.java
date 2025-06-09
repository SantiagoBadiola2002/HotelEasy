package com.hotel.hoteleasy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreUsuario;
    private String passwordHash;
    private String nombreCompleto;

    // Campo booleano para estado (activo/inactivo)
    private boolean activo;

    // Constructor sin parámetros
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(String nombreUsuario, String passwordHash, String nombreCompleto, boolean activo) {
        this.nombreUsuario = nombreUsuario;
        this.passwordHash = passwordHash;
        this.nombreCompleto = nombreCompleto;
        this.activo = activo;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
