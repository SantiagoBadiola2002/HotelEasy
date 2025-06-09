package com.hotel.hoteleasy.controller;

import com.hotel.hoteleasy.dtos.DTHabitacion;
import com.hotel.hoteleasy.enums.EstadoHabitacion;
import com.hotel.hoteleasy.enums.TipoHabitacion;
import com.hotel.hoteleasy.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @PostMapping
    public ResponseEntity<DTHabitacion> crearHabitacion(@RequestBody DTHabitacion habitacion) {
        DTHabitacion nuevaHabitacion = habitacionService.crearHabitacion(habitacion);
        return new ResponseEntity<>(nuevaHabitacion, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTHabitacion> obtenerHabitacionPorId(@PathVariable Long id) {
        DTHabitacion habitacion = habitacionService.obtenerHabitacionPorId(id);
        return new ResponseEntity<>(habitacion, HttpStatus.OK);
    }

    @GetMapping("/buscarPorNumero")
    public ResponseEntity<DTHabitacion> obtenerHabitacionPorNumero(@RequestParam String numero) {
        DTHabitacion habitacion = habitacionService.obtenerHabitacionPorNumero(numero);
        return new ResponseEntity<>(habitacion, HttpStatus.OK);
    }

    @GetMapping("/porPiso")
    public ResponseEntity<List<DTHabitacion>> obtenerHabitacionesPorPiso(@RequestParam int piso) {
        List<DTHabitacion> habitaciones = habitacionService.obtenerHabitacionesPorPiso(piso);
        return new ResponseEntity<>(habitaciones, HttpStatus.OK);
    }
    
    @GetMapping("/porTipoYEstado")
    public ResponseEntity<List<DTHabitacion>> obtenerPorTipoYEstado(@RequestParam TipoHabitacion tipoHabitacion, @RequestParam EstadoHabitacion estadoHabitacion) {
        List<DTHabitacion> habitaciones = habitacionService.obtenerHabitacionesPorTipoYEstado(tipoHabitacion, estadoHabitacion);
        return ResponseEntity.ok(habitaciones);
    }

    @GetMapping
    public ResponseEntity<List<DTHabitacion>> listarHabitaciones() {
        List<DTHabitacion> habitaciones = habitacionService.listarHabitaciones();
        return new ResponseEntity<>(habitaciones, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTHabitacion> actualizarHabitacion(@PathVariable Long id, @RequestBody DTHabitacion habitacion) {
        DTHabitacion habitacionActualizada = habitacionService.actualizarHabitacion(id, habitacion);
        return new ResponseEntity<>(habitacionActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHabitacion(@PathVariable Long id) {
        habitacionService.eliminarHabitacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/eliminarPorNumero")
    public ResponseEntity<Void> eliminarHabitacionPorNumero(@RequestParam String numero) {
        habitacionService.eliminarHabitacionPorNumero(numero);
        return ResponseEntity.noContent().build();
    }
}
