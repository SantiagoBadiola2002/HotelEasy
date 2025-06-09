package com.hotel.hoteleasy.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DTCliente {
    private Long id;
    private String nombreCompleto;
    private String ci;
    private String telefono;
    private String email;
    private String direccion;
    private LocalDateTime fechaRegistro;

    public Long getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCi() {
        return ci;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
}
