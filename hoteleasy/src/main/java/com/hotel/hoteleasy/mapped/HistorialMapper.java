package com.hotel.hoteleasy.mapped;

import com.hotel.hoteleasy.model.Historial;
import com.hotel.hoteleasy.dtos.DTHistorial;

public class HistorialMapper {

    public static DTHistorial toDTO(Historial historial) {
        if (historial == null) return null;

        return DTHistorial.builder()
                .id(historial.getId())
                .reservaId(historial.getReserva() != null ? historial.getReserva().getId() : null)
                .fechaCheckin(historial.getFechaCheckin())
                .fechaCheckout(historial.getFechaCheckout())
                .build();
    }

    public static Historial toEntity(DTHistorial dto) {
        if (dto == null) return null;

        Historial historial = new Historial();
        historial.setId(dto.getId());
        historial.setFechaCheckin(dto.getFechaCheckin());
        historial.setFechaCheckout(dto.getFechaCheckout());

        // Nota: Reserva  deben setearse por el servicio (capa de negocio)
        return historial;
    }
}
