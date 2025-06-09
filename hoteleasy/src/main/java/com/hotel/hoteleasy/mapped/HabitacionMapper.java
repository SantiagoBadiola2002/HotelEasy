package com.hotel.hoteleasy.mapped;

import com.hotel.hoteleasy.model.Habitacion;
import com.hotel.hoteleasy.model.ReservaHabitacion;
import com.hotel.hoteleasy.dtos.DTHabitacion;

import java.util.stream.Collectors;

public class HabitacionMapper {

    public static DTHabitacion toDTO(Habitacion habitacion) {
        if (habitacion == null) {
            return null;
        }

        return DTHabitacion.builder()
                .id(habitacion.getId())
                .numero(habitacion.getNumero())
                .tipo(habitacion.getTipo())
                .estado(habitacion.getEstado())
                .descripcion(habitacion.getDescripcion())
                .precioPorNoche(habitacion.getPrecioPorNoche())
                .numeroDePiso(habitacion.getNumeroDePiso())
                .reservasHabitacionIds(
                        habitacion.getReservasHabitacion() != null ?
                            habitacion.getReservasHabitacion().stream()
                                .map(ReservaHabitacion::getId)
                                .collect(Collectors.toList()) :
                            null
                )
                .build();
    }

    public static Habitacion toEntity(DTHabitacion dto) {
        if (dto == null) {
            return null;
        }

        Habitacion habitacion = new Habitacion();
        habitacion.setId(dto.getId());
        habitacion.setNumero(dto.getNumero());
        habitacion.setTipo(dto.getTipo());
        habitacion.setEstado(dto.getEstado());
        habitacion.setDescripcion(dto.getDescripcion());
        habitacion.setPrecioPorNoche(dto.getPrecioPorNoche());
        habitacion.setNumeroDePiso(dto.getNumeroDePiso());

        // No se asignan reservasHabitacion directamente porque se necesitan los objetos completos.
        // Este paso se debería hacer por separado con una lógica de servicio, si aplica.

        return habitacion;
    }
}
