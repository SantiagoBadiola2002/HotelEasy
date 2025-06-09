package com.hotel.hoteleasy.serviceimpl;

import com.hotel.hoteleasy.model.Cliente;
import com.hotel.hoteleasy.dtos.DTCliente;
import com.hotel.hoteleasy.mapped.ClienteMapper;
import com.hotel.hoteleasy.repository.ClienteRepository;
import com.hotel.hoteleasy.service.ClienteService;
import com.hotel.hoteleasy.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public DTCliente crearCliente(DTCliente dtCliente) {
        try {
            if (clienteRepository.findByCi(dtCliente.getCi()) != null) {
                throw new EntityExistsException("Ya existe un cliente con el CI: " + dtCliente.getCi());
            }
            Cliente cliente = ClienteMapper.toEntity(dtCliente);
            Cliente clienteGuardado = clienteRepository.save(cliente);
            return ClienteMapper.toDTO(clienteGuardado);
        } catch (DataIntegrityViolationException e) {
            throw new PersistenceException("Error de integridad al guardar el cliente: " + e.getMessage());
        }
    }

    @Override
    public DTCliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(ClienteMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Cliente con ID " + id + " no encontrado"));
    }

    @Override
    public DTCliente obtenerClientePorNombre(String nombreCompleto) {
        Cliente cliente = clienteRepository.findByNombreCompleto(nombreCompleto);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente con nombre " + nombreCompleto + " no encontrado");
        }
        return ClienteMapper.toDTO(cliente);
    }

    @Override
    public DTCliente obtenerClientePorCi(String ci) {
        Cliente cliente = clienteRepository.findByCi(ci);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente con CI " + ci + " no encontrado");
        }
        return ClienteMapper.toDTO(cliente);
    }

    @Override
    public List<DTCliente> obtenerClientesPorNombre(String nombre) {
        List<Cliente> clientes = clienteRepository.findByNombreCompletoContaining(nombre);
        if (clientes.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron clientes con nombre que contenga: " + nombre);
        }
        return clientes.stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DTCliente> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            throw new EntityNotFoundException("No hay clientes registrados");
        }
        return clientes.stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DTCliente actualizarCliente(Long id, DTCliente dtCliente) {
        if (!clienteRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede actualizar. Cliente con ID " + id + " no existe");
        }

        try {
            Cliente cliente = ClienteMapper.toEntity(dtCliente);
            cliente.setId(id);
            Cliente clienteActualizado = clienteRepository.save(cliente);
            return ClienteMapper.toDTO(clienteActualizado);
        } catch (DataIntegrityViolationException e) {
            throw new PersistenceException("Error al actualizar cliente: " + e.getMessage());
        }
    }

    @Override
    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Cliente con ID " + id + " no existe");
        }
        try {
            clienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new PersistenceException("Error al eliminar cliente: " + e.getMessage());
        }
    }
    
    @Override
public void eliminarClientePorCi(String ci) {
    Cliente cliente = clienteRepository.findByCi(ci);
    if (cliente == null) {
        throw new EntityNotFoundException("No se puede eliminar. Cliente con CI " + ci + " no existe");
    }
    try {
        clienteRepository.delete(cliente);
    } catch (Exception e) {
        throw new PersistenceException("Error al eliminar cliente con CI " + ci + ": " + e.getMessage());
    }
}

}