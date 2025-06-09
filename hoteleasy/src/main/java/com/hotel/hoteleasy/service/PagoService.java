package com.hotel.hoteleasy.service;

import com.hotel.hoteleasy.model.Pago;
import com.hotel.hoteleasy.dtos.DTPago;

import java.util.List;

public interface PagoService {
    DTPago crearPago(DTPago pago);
    DTPago obtenerPagoPorId(Long id);
    List<DTPago> obtenerPagos();
    List<DTPago> obtenerPagosPorReservaId(Long reservaId);
    void eliminarPago(Long id);
}
