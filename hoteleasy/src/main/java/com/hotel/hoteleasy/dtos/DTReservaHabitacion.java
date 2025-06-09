package com.hotel.hoteleasy.dtos;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DTReservaHabitacion {
    private Long id;
    private Long reservaId;
    private Long habitacionId;
    private BigDecimal precioFinal;

    public Long getId() {
        return id;
    }

    public Long getReservaId() {
        return reservaId;
    }

    public Long getHabitacionId() {
        return habitacionId;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }
}
