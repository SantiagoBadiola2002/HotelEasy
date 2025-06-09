package com.hotel.hoteleasy.dtos;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.hotel.hoteleasy.enums.EstadoReserva;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DTReserva {

    private Long id;
    private Long clienteId;
    private Long vendedorId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDateTime fechaReserva;
    private EstadoReserva estado;
    private List<DTReservaHabitacion> habitaciones;
    private List<DTPago> pagos; 
    private DTHistorial historial;

    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getVendedorId() {
        return vendedorId;
    }
    
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public List<DTReservaHabitacion> getHabitaciones() {
        return habitaciones;
    }

    public List<DTPago> getPagos() {
        return pagos;
    }

    public DTHistorial getHistorial() {
        return historial;
    }
    
    public DTReserva(Long id, Long clienteId, Long vendedorId, LocalDate fechaInicio, LocalDate fechaFin,
                 LocalDateTime fechaReserva, EstadoReserva estado, List<DTReservaHabitacion> habitaciones) {
    this.id = id;
    this.clienteId = clienteId;
    this.vendedorId = vendedorId;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
    this.fechaReserva = fechaReserva;
    this.estado = estado;
    this.habitaciones = habitaciones;
}


}
