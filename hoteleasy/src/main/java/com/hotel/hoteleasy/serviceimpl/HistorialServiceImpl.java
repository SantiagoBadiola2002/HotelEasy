package com.hotel.hoteleasy.serviceimpl;

import com.hotel.hoteleasy.dtos.DTHistorial;
import com.hotel.hoteleasy.mapped.HistorialMapper;
import com.hotel.hoteleasy.model.Historial;
import com.hotel.hoteleasy.model.Reserva;
import com.hotel.hoteleasy.repository.HistorialRepository;
import com.hotel.hoteleasy.repository.ReservaRepository;
import com.hotel.hoteleasy.service.HistorialService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistorialServiceImpl implements HistorialService {

    @Autowired
    private HistorialRepository historialRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public DTHistorial crearHistorial(DTHistorial dto) {
        Historial historial = HistorialMapper.toEntity(dto);

        Reserva reserva = reservaRepository.findById(dto.getReservaId())
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con ID: " + dto.getReservaId()));

        historial.setReserva(reserva);

        return HistorialMapper.toDTO(historialRepository.save(historial));
    }

    @Override
    @Transactional
    public DTHistorial realizarCheckin(Long reservaId, LocalDateTime fechaCheckin) {
        Historial historial = historialRepository.findByReservaId(reservaId)
                .orElseThrow(() -> new EntityNotFoundException("Historial no encontrado para la reserva con ID: " + reservaId));

        historial.setFechaCheckin(fechaCheckin);
        return HistorialMapper.toDTO(historialRepository.save(historial));
    }

    @Override
    @Transactional
    public DTHistorial realizarCheckout(Long reservaId, LocalDateTime fechaCheckout) {
        Historial historial = historialRepository.findByReservaId(reservaId)
                .orElseThrow(() -> new EntityNotFoundException("Historial no encontrado para la reserva con ID: " + reservaId));

        historial.setFechaCheckout(fechaCheckout);
        return HistorialMapper.toDTO(historialRepository.save(historial));
    }

    @Override
    public List<DTHistorial> obtenerHistorialPorId(Long id) {
        return historialRepository.findById(id)
                .map(historial -> List.of(HistorialMapper.toDTO(historial)))
                .orElseThrow(() -> new EntityNotFoundException("Historial con ID " + id + " no encontrado"));
    }

    @Override
    public List<DTHistorial> obtenerHistorialPorFechaCheckin(LocalDateTime fechaCheckin) {
        List<Historial> lista = historialRepository.findByFechaCheckin(fechaCheckin);
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("No hay historiales con fecha de check-in: " + fechaCheckin);
        }
        return lista.stream().map(HistorialMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<DTHistorial> obtenerHistorialPorFechaCheckout(LocalDateTime fechaCheckout) {
        List<Historial> lista = historialRepository.findByFechaCheckout(fechaCheckout);
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("No hay historiales con fecha de check-out: " + fechaCheckout);
        }
        return lista.stream().map(HistorialMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<DTHistorial> listarHistorial() {
        return historialRepository.findAll()
                .stream().map(HistorialMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public DTHistorial actualizarHistorial(Long id, DTHistorial dto) {
        Historial historialExistente = historialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Historial con ID " + id + " no encontrado"));

        historialExistente.setFechaCheckin(dto.getFechaCheckin());
        historialExistente.setFechaCheckout(dto.getFechaCheckout());

        return HistorialMapper.toDTO(historialRepository.save(historialExistente));
    }

    @Override
    public void eliminarHistorial(Long id) {
        if (!historialRepository.existsById(id)) {
            throw new EntityNotFoundException("Historial con ID " + id + " no encontrado");
        }
        historialRepository.deleteById(id);
    }
}
