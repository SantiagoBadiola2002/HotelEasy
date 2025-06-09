package com.hotel.hoteleasy.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DTUsuario {
    private Long id;
    private String nombreUsuario;
    private String passwordHash;
    private String nombreCompleto;
    private boolean activo;
    
    public Long getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public boolean isActivo() {
        return activo;
    }
}