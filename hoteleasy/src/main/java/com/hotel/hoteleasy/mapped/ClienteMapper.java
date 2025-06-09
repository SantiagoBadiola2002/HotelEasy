package com.hotel.hoteleasy.mapped;

import com.hotel.hoteleasy.model.Cliente;
import com.hotel.hoteleasy.dtos.DTCliente;

public class ClienteMapper {

    // De entidad Cliente a DTO DTCliente
    public static DTCliente toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        return DTCliente.builder()
                .id(cliente.getId())
                .nombreCompleto(cliente.getNombreCompleto())
                .ci(cliente.getCi())
                .telefono(cliente.getTelefono())
                .email(cliente.getEmail())
                .direccion(cliente.getDireccion())
                .fechaRegistro(cliente.getFechaRegistro())
                .build();
    }

    // De DTO DTCliente a entidad Cliente
    public static Cliente toEntity(DTCliente dtCliente) {
        if (dtCliente == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setId(dtCliente.getId());
        cliente.setNombreCompleto(dtCliente.getNombreCompleto());
        cliente.setCi(dtCliente.getCi());
        cliente.setTelefono(dtCliente.getTelefono());
        cliente.setEmail(dtCliente.getEmail());
        cliente.setDireccion(dtCliente.getDireccion());
        cliente.setFechaRegistro(dtCliente.getFechaRegistro());

        return cliente;
    }
}
