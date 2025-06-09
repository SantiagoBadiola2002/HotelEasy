package com.hotel.hoteleasy.controller;

import com.hotel.hoteleasy.dtos.DTPago;
import com.hotel.hoteleasy.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public ResponseEntity<DTPago> crearPago(@RequestBody DTPago pagoDTO) {
        System.out.println("\nPOST /api/pagos -> crearPago()");
        System.out.println("Datos recibidos:");
        System.out.println("Reserva ID: " + pagoDTO.getReservaId());
        System.out.println("Monto: " + pagoDTO.getMonto());
        System.out.println("Método de Pago: " + pagoDTO.getMetodoPago());
        System.out.println("Fecha de Pago: " + pagoDTO.getFechaPago());

        DTPago nuevoPago = pagoService.crearPago(pagoDTO);

        System.out.println("Pago creado con ID: " + nuevoPago.getId() + "\n");
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DTPago>> obtenerTodosLosPagos() {
        System.out.println("\nGET /api/pagos -> obtenerTodosLosPagos()");
        List<DTPago> pagos = pagoService.obtenerPagos();
        System.out.println("Cantidad de pagos encontrados: " + pagos.size());
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTPago> obtenerPagoPorId(@PathVariable Long id) {
        System.out.println("\nGET /api/pagos/" + id + " -> obtenerPagoPorId()");
        DTPago pago = pagoService.obtenerPagoPorId(id);
        System.out.println("Pago encontrado - ID: " + pago.getId() + ", Monto: " + pago.getMonto());
        return new ResponseEntity<>(pago, HttpStatus.OK);
    }

    @GetMapping("/por-reserva/{reservaId}")
    public ResponseEntity<List<DTPago>> obtenerPagosPorReservaId(@PathVariable Long reservaId) {
        System.out.println("\nGET /api/pagos/por-reserva/" + reservaId + " -> obtenerPagosPorReservaId()");
        List<DTPago> pagos = pagoService.obtenerPagosPorReservaId(reservaId);
        System.out.println("Pagos encontrados para reserva ID " + reservaId + ": " + pagos.size());
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        System.out.println("\nDELETE /api/pagos/" + id + " -> eliminarPago()");
        pagoService.eliminarPago(id);
        System.out.println("Pago con ID " + id + " eliminado correctamente.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
