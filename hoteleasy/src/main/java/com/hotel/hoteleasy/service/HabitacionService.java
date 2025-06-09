package com.hotel.hoteleasy.service;

import com.hotel.hoteleasy.dtos.DTHabitacion;
import com.hotel.hoteleasy.enums.EstadoHabitacion;
import com.hotel.hoteleasy.enums.TipoHabitacion;
import java.util.List;

public interface HabitacionService {
    DTHabitacion crearHabitacion(DTHabitacion habitacion);
    DTHabitacion obtenerHabitacionPorId(Long id);
    DTHabitacion obtenerHabitacionPorNumero(String numero);
    List<DTHabitacion> obtenerHabitacionesPorPiso(int numeroPiso);
    List<DTHabitacion> obtenerHabitacionesPorEstado(EstadoHabitacion estadoHabitacion);
    List<DTHabitacion> obtenerHabitacionesPorTipoYEstado(TipoHabitacion tipoHabitacion, EstadoHabitacion estadoHabitacion);
    List<DTHabitacion> listarHabitaciones();
    DTHabitacion actualizarHabitacion(Long id, DTHabitacion habitacion);
    void eliminarHabitacion(Long id);
    void eliminarHabitacionPorNumero(String numero);
}
