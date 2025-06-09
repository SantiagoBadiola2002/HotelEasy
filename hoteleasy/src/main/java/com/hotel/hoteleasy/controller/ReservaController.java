package com.hotel.hoteleasy.controller;

import com.hotel.hoteleasy.dtos.DTReserva;
import com.hotel.hoteleasy.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<DTReserva> crearReserva(@RequestBody DTReserva reservaDTO) {
        System.out.println("\nPOST: crearReserva");
        System.out.println("fechaInicio: " + reservaDTO.getFechaInicio()
        + " fechaFin: " + reservaDTO.getFechaFin() + " fechaReserva: " + reservaDTO.getFechaReserva()
        + " estado: " + reservaDTO.getEstado() + " clienteId: " + reservaDTO.getClienteId() + " habitacionId: " + reservaDTO.getHabitaciones() + "\n");
        DTReserva nuevaReserva = reservaService.crearReserva(reservaDTO);
        return new ResponseEntity<>(nuevaReserva, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DTReserva>> obtenerTodasLasReservas() {
        List<DTReserva> reservas = reservaService.obtenerReservas();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTReserva> obtenerReservaPorId(@PathVariable Long id) {
        DTReserva reserva = reservaService.obtenerReservaPorId(id);
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }

    @GetMapping("/por-cliente/{clienteId}")
    public ResponseEntity<List<DTReserva>> obtenerReservasPorClienteId(@PathVariable Long clienteId) {
        List<DTReserva> reservas = reservaService.obtenerReservasPorClienteId(clienteId);
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }
    
    @GetMapping("/por-vendedor/{vendedorId}")
    public ResponseEntity<List<DTReserva>> obtenerReservasPorVendedorId(@PathVariable Long vendedorId) {
        List<DTReserva> reservas = reservaService.obtenerReservasPorVendedorId(vendedorId);
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<DTReserva> actualizarEstadoReserva(
            @PathVariable Long id,
            @RequestParam String nuevoEstado) {
        DTReserva reservaActualizada = reservaService.actualizarEstado(id, nuevoEstado);
        return new ResponseEntity<>(reservaActualizada, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
public ResponseEntity<DTReserva> editarReserva(@PathVariable Long id, @RequestBody DTReserva reservaDTO) {
    DTReserva reservaEditada = reservaService.actualizarReserva(id, reservaDTO);
    return new ResponseEntity<>(reservaEditada, HttpStatus.OK);
}

@PutMapping("/cambiarHabitacion")
public ResponseEntity<DTReserva> cambiarHabitacion(@RequestParam Long reservaId, @RequestParam Long habitacionActualId, @RequestParam Long nuevaHabitacionId) {
    DTReserva reservaActualizada = reservaService.cambiarHabitacionDeReserva(reservaId, habitacionActualId, nuevaHabitacionId);
    return ResponseEntity.ok(reservaActualizada);
}



}
