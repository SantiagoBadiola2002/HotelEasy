package com.hotel.hoteleasy.mapped;

import com.hotel.hoteleasy.model.ReservaHabitacion;
import com.hotel.hoteleasy.dtos.DTReservaHabitacion;

public class ReservaHabitacionMapper {

    public static DTReservaHabitacion toDTO(ReservaHabitacion rh) {
        if (rh == null) return null;

        return DTReservaHabitacion.builder()
            .id(rh.getId())
            .reservaId(rh.getReserva() != null ? rh.getReserva().getId() : null)
            .habitacionId(rh.getHabitacion() != null ? rh.getHabitacion().getId() : null)
            .precioFinal(rh.getPrecioFinal())
            .build();
    }

    public static ReservaHabitacion toEntity(DTReservaHabitacion dto) {
        if (dto == null) return null;

        ReservaHabitacion rh = new ReservaHabitacion();
        rh.setId(dto.getId());
        rh.setPrecioFinal(dto.getPrecioFinal());

        // rh.setReserva(...) y rh.setHabitacion(...) se deben asignar en el servicio usando dto.getReservaId() y dto.getHabitacionId()

        return rh;
    }
}
