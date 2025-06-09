package com.hotel.hoteleasy.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.hotel.hoteleasy.enums.MetodoPago;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DTPago {

    private Long id;
    private BigDecimal monto;
    private MetodoPago metodoPago;
    private LocalDateTime fechaPago;
    private Long reservaId;

    public Long getId() {
        return id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public Long getReservaId() {
        return reservaId;
    }
}
