package com.hotel.hoteleasy.controller;

import com.hotel.hoteleasy.dtos.DTCliente;
import com.hotel.hoteleasy.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<DTCliente> crearCliente(@RequestBody DTCliente cliente) {
        DTCliente nuevoCliente = clienteService.crearCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTCliente> obtenerClientePorId(@PathVariable Long id) {
        DTCliente cliente = clienteService.obtenerClientePorId(id);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscarPorNombreCompleto")
    public ResponseEntity<DTCliente> obtenerClientePorNombreCompleto(@RequestParam String nombreCompleto) {
        DTCliente cliente = clienteService.obtenerClientePorNombre(nombreCompleto);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscarPorCi")
    public ResponseEntity<DTCliente> obtenerClientePorCi(@RequestParam String ci) {
        DTCliente cliente = clienteService.obtenerClientePorCi(ci);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<DTCliente>> obtenerClientesPorNombre(@RequestParam String nombre) {
        List<DTCliente> clientes = clienteService.obtenerClientesPorNombre(nombre);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DTCliente>> listarClientes() {
        List<DTCliente> clientes = clienteService.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTCliente> actualizarCliente(@PathVariable Long id, @RequestBody DTCliente cliente) {
        DTCliente clienteActualizado = clienteService.actualizarCliente(id, cliente);
        if (clienteActualizado != null) {
            return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping("/eliminarPorCi")
    public ResponseEntity<Void> eliminarClientePorCi(@RequestParam String ci) {
        clienteService.eliminarClientePorCi(ci);
        return ResponseEntity.noContent().build();
    }

}
