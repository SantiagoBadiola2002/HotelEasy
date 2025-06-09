package com.hotel.hoteleasy.repository;

import com.hotel.hoteleasy.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public interface HistorialRepository extends JpaRepository<Historial, Long> {
    // Aquí puedes agregar métodos personalizados si los necesitas
    Optional<Historial> findByReservaId(Long reservaId);
    List<Historial> findByFechaCheckin(LocalDateTime fechaCheckin);
    List<Historial> findByFechaCheckout(LocalDateTime fechaCheckout);

}
