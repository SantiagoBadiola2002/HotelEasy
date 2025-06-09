package com.hotel.hoteleasy.service;

import com.hotel.hoteleasy.dtos.DTCliente;
import java.util.List;

public interface ClienteService {
    DTCliente crearCliente(DTCliente cliente);
    DTCliente obtenerClientePorId(Long id);
    DTCliente obtenerClientePorNombre(String nombreCompleto);
    DTCliente obtenerClientePorCi(String ci);
    List<DTCliente> obtenerClientesPorNombre(String nombre);
    List<DTCliente> listarClientes();
    DTCliente actualizarCliente(Long id, DTCliente cliente);
    void eliminarCliente(Long id);
    void eliminarClientePorCi(String ci);
}
