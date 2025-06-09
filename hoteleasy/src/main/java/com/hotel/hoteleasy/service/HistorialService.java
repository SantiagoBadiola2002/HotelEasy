package com.hotel.hoteleasy.service;

import com.hotel.hoteleasy.dtos.DTHistorial;

import java.util.List;
import java.time.LocalDateTime;

public interface HistorialService {
    DTHistorial crearHistorial(DTHistorial historial);
    DTHistorial realizarCheckin(Long reservaId, LocalDateTime fechaCheckin);
    DTHistorial realizarCheckout(Long reservaId, LocalDateTime fechaCheckout);
    List<DTHistorial> obtenerHistorialPorId(Long id);
    List<DTHistorial> obtenerHistorialPorFechaCheckin(LocalDateTime fechaCheckin);
    List<DTHistorial> obtenerHistorialPorFechaCheckout(LocalDateTime fechaCheckout);
    List<DTHistorial> listarHistorial();
    DTHistorial actualizarHistorial(Long id, DTHistorial historial);
    void eliminarHistorial(Long id);
}
