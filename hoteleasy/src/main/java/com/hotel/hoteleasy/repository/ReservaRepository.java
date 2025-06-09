package com.hotel.hoteleasy.repository;

import com.hotel.hoteleasy.model.Reserva;
import com.hotel.hoteleasy.enums.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDate;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Aquí puedes agregar métodos personalizados si los necesitas
    List<Reserva> findByClienteId(Long clienteId);
    List<Reserva> findByClienteCi(String ci); //HistorialRepository?
    List<Reserva> findByVendedorId(Long vendedorId);
    List<Reserva> findByClienteNombreCompletoContainingIgnoreCase(String nombre);
    List<Reserva> findByEstado(EstadoReserva estado);
    List<Reserva> findByFechaInicioBetween(LocalDate inicio, LocalDate fin);
    List<Reserva> findByFechaFinBetween(LocalDate inicio, LocalDate fin);
    List<Reserva> findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(LocalDate fechaInicio, LocalDate fechaFin);
    List<Reserva> findByClienteIdAndEstado(Long clienteId, EstadoReserva estado);
    
    //List<Reserva> findByHabitacionId(Long habitacionId); ReservaHabitacionRepository?
    //List<Reserva> findByHabitacionNumero(String numero);

}
