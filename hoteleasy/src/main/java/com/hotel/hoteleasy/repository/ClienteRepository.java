package com.hotel.hoteleasy.repository;

import com.hotel.hoteleasy.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByNombreCompleto(String nombreCompleto);  // Método actualizado
    Cliente findByCi(String ci);
    List<Cliente> findByNombreCompletoContaining(String nombre);
}