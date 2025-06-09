package com.hotel.hoteleasy.service;

import com.hotel.hoteleasy.dtos.DTReserva;
import java.util.List;

public interface ReservaService {
    DTReserva crearReserva(DTReserva reservaDTO);
    DTReserva obtenerReservaPorId(Long id);
    List<DTReserva> obtenerReservas();
    List<DTReserva> obtenerReservasPorClienteId(Long clienteId);
    List<DTReserva> obtenerReservasPorVendedorId(Long vendedorId);
    DTReserva actualizarEstado(Long reservaId, String nuevoEstado);
    DTReserva actualizarReserva(Long id, DTReserva reservaDTO);
    DTReserva cambiarHabitacionDeReserva(Long reservaId, Long habitacionActualId, Long nuevaHabitacionId);
}
