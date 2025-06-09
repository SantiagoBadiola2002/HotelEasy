package com.hotel.hoteleasy.mapped;

import com.hotel.hoteleasy.model.Pago;
import com.hotel.hoteleasy.dtos.DTPago;

public class PagoMapper {

    public static DTPago toDTO(Pago pago) {
        if (pago == null) return null;

        return DTPago.builder()
                .id(pago.getId())
                .monto(pago.getMonto())
                .metodoPago(pago.getMetodoPago())
                .fechaPago(pago.getFechaPago())
                .reservaId(pago.getReserva() != null ? pago.getReserva().getId() : null)
                .build();
    }

    public static Pago toEntity(DTPago dto) {
        if (dto == null) return null;

        Pago pago = new Pago();
        pago.setId(dto.getId());
        pago.setMonto(dto.getMonto());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setFechaPago(dto.getFechaPago());

        // pago.setReserva(...) debe ser manejado por el servicio a partir de dto.getReservaId()

        return pago;
    }
}
