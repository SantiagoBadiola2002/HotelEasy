package com.hotel.hoteleasy.repository;

import java.math.BigDecimal;
import java.util.List;
import com.hotel.hoteleasy.enums.*;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.hoteleasy.model.Habitacion;
import java.util.Optional;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    List<Habitacion> findByEstado(EstadoHabitacion estado);
    List<Habitacion> findByTipoAndEstado(TipoHabitacion tipo, EstadoHabitacion estado);
    List<Habitacion> findByTipo(TipoHabitacion tipo);
    Optional<Habitacion> findByNumero(String numero);
    List<Habitacion> findByNumeroDePiso(int numeroDePiso);
}
