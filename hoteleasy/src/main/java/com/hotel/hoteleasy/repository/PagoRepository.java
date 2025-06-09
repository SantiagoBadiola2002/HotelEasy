package com.hotel.hoteleasy.repository;

import com.hotel.hoteleasy.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    // Aquí puedes agregar métodos personalizados si los necesitas
    List<Pago> findByReservaId(Long reservaId);
}
