package com.hotel.hoteleasy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;  // Importa la anotación Column

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCompleto;

    @Column(unique = true)  // Asegura que el CI sea único
    private String ci;

    private String telefono;
    private String email;
    private String direccion;

    private LocalDateTime fechaRegistro;

    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;

    // Constructor sin parámetros
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(String nombreCompleto, String ci, String telefono, String email, String direccion, LocalDateTime fechaRegistro) {
        this.nombreCompleto = nombreCompleto;
        this.ci = ci;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
