package com.hotel.hoteleasy.serviceimpl;

import com.hotel.hoteleasy.model.Cliente;
import com.hotel.hoteleasy.model.Habitacion;
import com.hotel.hoteleasy.model.Historial;
import com.hotel.hoteleasy.model.Reserva;
import com.hotel.hoteleasy.model.Usuario;
import com.hotel.hoteleasy.model.ReservaHabitacion;
import com.hotel.hoteleasy.enums.EstadoReserva;
import com.hotel.hoteleasy.enums.EstadoHabitacion;
import com.hotel.hoteleasy.dtos.DTReserva;
import com.hotel.hoteleasy.dtos.DTReservaHabitacion;
import com.hotel.hoteleasy.mapped.ReservaMapper;
import com.hotel.hoteleasy.repository.ReservaRepository;
import com.hotel.hoteleasy.repository.ClienteRepository;
import com.hotel.hoteleasy.repository.HabitacionRepository;
import com.hotel.hoteleasy.repository.HistorialRepository;
import com.hotel.hoteleasy.repository.UsuarioRepository;
import com.hotel.hoteleasy.service.ReservaService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.springframework.dao.DataIntegrityViolationException;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;


@Service
public class ReservaServiceImpl implements ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;
    
    @Autowired
    private HistorialRepository historialRepository;
    
     @Autowired
    private UsuarioRepository usuarioRepository;


   @Override
public DTReserva crearReserva(DTReserva reservaDTO) {
    Reserva reserva = ReservaMapper.toEntity(reservaDTO);

    Cliente cliente = clienteRepository.findById(reservaDTO.getClienteId())
                         .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    reserva.setCliente(cliente);

    // Buscar y setear vendedor
    Usuario vendedor = usuarioRepository.findById(reservaDTO.getVendedorId())
                         .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
    reserva.setVendedor(vendedor);

    long dias = ChronoUnit.DAYS.between(reservaDTO.getFechaInicio(), reservaDTO.getFechaFin());
    if (dias <= 0) {
        throw new IllegalArgumentException("La fecha de fin debe ser posterior a la de inicio");
    }

    List<ReservaHabitacion> habitaciones = new ArrayList<>();
    if (reservaDTO.getHabitaciones() != null) {
        for (DTReservaHabitacion dtoHab : reservaDTO.getHabitaciones()) {
            Habitacion habitacion = habitacionRepository.findById(dtoHab.getHabitacionId())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

            ReservaHabitacion rh = new ReservaHabitacion();
            rh.setHabitacion(habitacion);
            rh.setReserva(reserva);

            BigDecimal precioPorNoche = habitacion.getPrecioPorNoche(); 
            BigDecimal precioFinal = precioPorNoche.multiply(BigDecimal.valueOf(dias));
            rh.setPrecioFinal(precioFinal);

            habitaciones.add(rh);
        }
    }

    reserva.setHabitaciones(habitaciones);
    Reserva reservaGuardada = reservaRepository.save(reserva);

    // Crear historial vacío asociado a la reserva
    Historial historial = new Historial();
    historial.setReserva(reservaGuardada);
    historialRepository.save(historial);  

    return ReservaMapper.toDTO(reservaGuardada);
}





    @Override
    public DTReserva obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id)
                .map(ReservaMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Reserva con ID " + id + " no encontrada"));
    }

    @Override
    public List<DTReserva> obtenerReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        if (reservas.isEmpty()) {
            throw new EntityNotFoundException("No hay reservas registradas");
        }
        return reservas.stream()
                .map(ReservaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DTReserva> obtenerReservasPorClienteId(Long clienteId) {
        List<Reserva> reservas = reservaRepository.findByClienteId(clienteId);
        if (reservas.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron reservas para el cliente con ID: " + clienteId);
        }
        return reservas.stream()
                .map(ReservaMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<DTReserva> obtenerReservasPorVendedorId(Long vendedorId) {
        List<Reserva> reservas = reservaRepository.findByVendedorId(vendedorId);
        if (reservas.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron reservas para el vendedor con ID: " + vendedorId);
        }
        return reservas.stream()
                .map(ReservaMapper::toDTO)
                .collect(Collectors.toList());
    }

   @Override
public DTReserva actualizarEstado(Long reservaId, String nuevoEstado) {
    Reserva reserva = reservaRepository.findById(reservaId)
            .orElseThrow(() -> new EntityNotFoundException("Reserva con ID " + reservaId + " no encontrada"));

    try {
        EstadoReserva estado = EstadoReserva.valueOf(nuevoEstado.toUpperCase());
        reserva.setEstado(estado);
        Reserva reservaActualizada = reservaRepository.save(reserva);
        return ReservaMapper.toDTO(reservaActualizada);
    } catch (IllegalArgumentException e) {
        throw new PersistenceException("Estado de reserva no válido: " + nuevoEstado +
                ". Los valores válidos son: PENDIENTE, CONFIRMADA, CANCELADA, COMPLETADA");
    } catch (DataIntegrityViolationException e) {
        throw new PersistenceException("Error al actualizar el estado de la reserva: " + e.getMessage());
    }
}

@Override
public DTReserva actualizarReserva(Long id, DTReserva reservaDTO) {
    Reserva reservaExistente = reservaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Reserva con ID " + id + " no encontrada"));

    try {
        Reserva reservaActualizada = ReservaMapper.toEntity(reservaDTO);
        reservaActualizada.setId(id);  // aseguramos mantener el ID
        reservaActualizada.setCliente(reservaExistente.getCliente()); // opcional: mantener cliente si no se cambia
        Reserva guardada = reservaRepository.save(reservaActualizada);
        return ReservaMapper.toDTO(guardada);
    } catch (DataIntegrityViolationException e) {
        throw new PersistenceException("Error al actualizar reserva: " + e.getMessage());
    }
}

@Override
public DTReserva cambiarHabitacionDeReserva(Long reservaId, Long habitacionActualId, Long nuevaHabitacionId) {
    Reserva reserva = reservaRepository.findById(reservaId)
            .orElseThrow(() -> new EntityNotFoundException("Reserva con ID " + reservaId + " no encontrada"));

    Habitacion nuevaHabitacion = habitacionRepository.findById(nuevaHabitacionId)
            .orElseThrow(() -> new EntityNotFoundException("Nueva habitación con ID " + nuevaHabitacionId + " no encontrada"));

    // Verificamos si la nueva habitación está disponible
    if (nuevaHabitacion.getEstado() != EstadoHabitacion.DISPONIBLE) {
        throw new IllegalStateException("La nueva habitación no está disponible");
    }

    List<ReservaHabitacion> habitaciones = reserva.getHabitaciones();
    boolean encontrada = false;
    long dias = ChronoUnit.DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin());

    for (ReservaHabitacion rh : habitaciones) {
        if (rh.getHabitacion().getId().equals(habitacionActualId)) {
            rh.setHabitacion(nuevaHabitacion);
            rh.setPrecioFinal(nuevaHabitacion.getPrecioPorNoche().multiply(BigDecimal.valueOf(dias)));
            encontrada = true;
            break;
        }
    }

    if (!encontrada) {
        throw new EntityNotFoundException("La habitación actual no está asociada a la reserva");
    }

    // Guardamos y devolvemos
    Reserva actualizada = reservaRepository.save(reserva);
    return ReservaMapper.toDTO(actualizada);
}



}
