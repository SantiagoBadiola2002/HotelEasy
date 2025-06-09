package com.hotel.hoteleasy.mapped;

import com.hotel.hoteleasy.model.Reserva;
import com.hotel.hoteleasy.dtos.DTReserva;
import com.hotel.hoteleasy.dtos.DTHabitacion;
import com.hotel.hoteleasy.dtos.DTPago;
import com.hotel.hoteleasy.dtos.DTHistorial;
import com.hotel.hoteleasy.dtos.DTReservaHabitacion;
import com.hotel.hoteleasy.mapped.PagoMapper;
import com.hotel.hoteleasy.mapped.HistorialMapper;
import com.hotel.hoteleasy.mapped.ReservaHabitacionMapper;

import java.util.stream.Collectors;
import java.util.List;

public class ReservaMapper {

    public static DTReserva toDTO(Reserva reserva) {
        if (reserva == null) return null;

        List<DTReservaHabitacion> habitacionesDTO = null;
        if (reserva.getHabitaciones() != null) {
            habitacionesDTO = reserva.getHabitaciones()
                .stream()
                .map(ReservaHabitacionMapper::toDTO)
                .collect(Collectors.toList());
        }

        List<DTPago> pagosDTO = null;
        if (reserva.getPagos() != null) {
            pagosDTO = reserva.getPagos()
                .stream()
                .map(PagoMapper::toDTO)
                .collect(Collectors.toList());
        }

        DTHistorial historialDTO = HistorialMapper.toDTO(reserva.getHistorial());

        return DTReserva.builder()
            .id(reserva.getId())
            .clienteId(reserva.getCliente() != null ? reserva.getCliente().getId() : null)
            .vendedorId(reserva.getVendedor() != null ? reserva.getVendedor().getId() : null) // nuevo campo
            .fechaInicio(reserva.getFechaInicio())
            .fechaFin(reserva.getFechaFin())
            .fechaReserva(reserva.getFechaReserva())
            .estado(reserva.getEstado())
            .habitaciones(habitacionesDTO)
            .pagos(pagosDTO)
            .historial(historialDTO)
            .build();
    }

    public static Reserva toEntity(DTReserva dto) {
        if (dto == null) return null;

        Reserva reserva = new Reserva();

        reserva.setId(dto.getId());
        // Cliente debe asignarse fuera del mapper (en el servicio) usando dto.getClienteId()
        // Vendedor debe asignarse fuera del mapper (en el servicio) usando dto.getVendedorId()

        reserva.setFechaInicio(dto.getFechaInicio());
        reserva.setFechaFin(dto.getFechaFin());
        reserva.setFechaReserva(dto.getFechaReserva());
        reserva.setEstado(dto.getEstado());

        if (dto.getHabitaciones() != null) {
            reserva.setHabitaciones(dto.getHabitaciones()
                .stream()
                .map(ReservaHabitacionMapper::toEntity)
                .collect(Collectors.toList()));
        }

        if (dto.getPagos() != null) {
            reserva.setPagos(dto.getPagos()
                .stream()
                .map(PagoMapper::toEntity)
                .collect(Collectors.toList()));
        }

        reserva.setHistorial(HistorialMapper.toEntity(dto.getHistorial()));

        return reserva;
    }
}
