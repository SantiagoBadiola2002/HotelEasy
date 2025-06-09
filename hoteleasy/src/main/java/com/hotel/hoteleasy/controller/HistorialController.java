package com.hotel.hoteleasy.controller;

import com.hotel.hoteleasy.dtos.DTHistorial;
import com.hotel.hoteleasy.service.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    // Crear un historial
    @PostMapping
    public ResponseEntity<DTHistorial> crearHistorial(@RequestBody DTHistorial dto) {
        DTHistorial creado = historialService.crearHistorial(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    // Realizar check-in
    @PutMapping("/checkin/{reservaId}")
    public ResponseEntity<DTHistorial> realizarCheckin(
            @PathVariable Long reservaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaCheckin) {
        DTHistorial actualizado = historialService.realizarCheckin(reservaId, fechaCheckin);
        return ResponseEntity.ok(actualizado);
    }

    // Realizar check-out
    @PutMapping("/checkout/{reservaId}")
    public ResponseEntity<DTHistorial> realizarCheckout(
            @PathVariable Long reservaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaCheckout) {
        DTHistorial actualizado = historialService.realizarCheckout(reservaId, fechaCheckout);
        return ResponseEntity.ok(actualizado);
    }

    // Obtener historial por ID
    @GetMapping("/{id}")
    public ResponseEntity<List<DTHistorial>> obtenerPorId(@PathVariable Long id) {
        List<DTHistorial> resultado = historialService.obtenerHistorialPorId(id);
        return ResponseEntity.ok(resultado);
    }

    // Obtener historial por fecha de check-in
    @GetMapping("/checkin")
    public ResponseEntity<List<DTHistorial>> obtenerPorFechaCheckin(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaCheckin) {
        List<DTHistorial> resultado = historialService.obtenerHistorialPorFechaCheckin(fechaCheckin);
        return ResponseEntity.ok(resultado);
    }

    // Obtener historial por fecha de check-out
    @GetMapping("/checkout")
    public ResponseEntity<List<DTHistorial>> obtenerPorFechaCheckout(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaCheckout) {
        List<DTHistorial> resultado = historialService.obtenerHistorialPorFechaCheckout(fechaCheckout);
        return ResponseEntity.ok(resultado);
    }

    // Listar todos los historiales
    @GetMapping
    public ResponseEntity<List<DTHistorial>> listarTodos() {
        return ResponseEntity.ok(historialService.listarHistorial());
    }

    // Actualizar historial
    @PutMapping("/{id}")
    public ResponseEntity<DTHistorial> actualizar(@PathVariable Long id, @RequestBody DTHistorial dto) {
        DTHistorial actualizado = historialService.actualizarHistorial(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar historial
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        historialService.eliminarHistorial(id);
        return ResponseEntity.noContent().build();
    }
}
