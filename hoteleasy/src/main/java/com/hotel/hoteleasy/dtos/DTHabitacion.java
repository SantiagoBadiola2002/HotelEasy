package com.hotel.hoteleasy.dtos;

import java.math.BigDecimal;
import java.util.List;
import com.hotel.hoteleasy.enums.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DTHabitacion {

    private Long id;
    private String numero;
    private TipoHabitacion tipo;
    private EstadoHabitacion estado;
    private String descripcion;
    private BigDecimal precioPorNoche;
    private int numeroDePiso;
    private List<Long> reservasHabitacionIds;

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public EstadoHabitacion getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrecioPorNoche() {
        return precioPorNoche;
    }

    public int getNumeroDePiso() {
        return numeroDePiso;
    }

    public List<Long> getReservasHabitacionIds() {
        return reservasHabitacionIds;
    }
}
