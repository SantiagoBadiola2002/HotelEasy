package com.hotel.hoteleasy.dtos;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DTHistorial {

    private Long id;
    private Long reservaId;
    private LocalDateTime fechaCheckin;
    private LocalDateTime fechaCheckout;

    public Long getId() {
        return id;
    }

    public Long getReservaId() {
        return reservaId;
    }

    public LocalDateTime getFechaCheckin() {
        return fechaCheckin;
    }

    public LocalDateTime getFechaCheckout() {
        return fechaCheckout;
    }

}
