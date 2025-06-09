package com.hotel.hoteleasy.serviceimpl;

import com.hotel.hoteleasy.dtos.DTHabitacion;
import com.hotel.hoteleasy.enums.EstadoHabitacion;
import com.hotel.hoteleasy.enums.TipoHabitacion;
import com.hotel.hoteleasy.exception.GlobalExceptionHandler;
import com.hotel.hoteleasy.mapped.HabitacionMapper;
import com.hotel.hoteleasy.model.Habitacion;
import com.hotel.hoteleasy.repository.HabitacionRepository;
import com.hotel.hoteleasy.service.HabitacionService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HabitacionServiceImpl implements HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Override
    public DTHabitacion crearHabitacion(DTHabitacion dtHabitacion) {
        try {
            if (habitacionRepository.findByNumero(dtHabitacion.getNumero()).isPresent()) {
                throw new EntityExistsException("Ya existe una habitación con el número: " + dtHabitacion.getNumero());
            }
            Habitacion habitacion = HabitacionMapper.toEntity(dtHabitacion);
            Habitacion guardada = habitacionRepository.save(habitacion);
            return HabitacionMapper.toDTO(guardada);
        } catch (DataIntegrityViolationException e) {
            throw new PersistenceException("Error de integridad al guardar la habitación: " + e.getMessage());
        }
    }

    @Override
    public DTHabitacion obtenerHabitacionPorId(Long id) {
        return habitacionRepository.findById(id)
                .map(HabitacionMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Habitación con ID " + id + " no encontrada"));
    }

    @Override
    public DTHabitacion obtenerHabitacionPorNumero(String numero) {
        Habitacion habitacion = habitacionRepository.findByNumero(numero)
                .orElseThrow(() -> new EntityNotFoundException("Habitación con número " + numero + " no encontrada"));
        return HabitacionMapper.toDTO(habitacion);
    }

    @Override
    public List<DTHabitacion> obtenerHabitacionesPorPiso(int numeroPiso) {
        List<Habitacion> habitaciones = habitacionRepository.findAll()
                .stream()
                .filter(h -> h.getNumeroDePiso() == numeroPiso)
                .collect(Collectors.toList());

        if (habitaciones.isEmpty()) {
            throw new EntityNotFoundException("No hay habitaciones registradas en el piso " + numeroPiso);
        }

        return habitaciones.stream()
                .map(HabitacionMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<DTHabitacion> obtenerHabitacionesPorEstado(EstadoHabitacion estadoHabitacion) {
        List<Habitacion> habitaciones = habitacionRepository.findByEstado(estadoHabitacion);

        if (habitaciones.isEmpty()) {
         throw new EntityNotFoundException("No hay habitaciones en estado: " + estadoHabitacion);
     }

     return habitaciones.stream()
                .map(HabitacionMapper::toDTO)
                .collect(Collectors.toList());
    }

    
    @Override
    public List<DTHabitacion> obtenerHabitacionesPorTipoYEstado(TipoHabitacion tipoHabitacion, EstadoHabitacion estadoHabitacion) {
      List<Habitacion> habitaciones = habitacionRepository.findByTipoAndEstado(tipoHabitacion, estadoHabitacion);

     if (habitaciones.isEmpty()) {
         throw new EntityNotFoundException("No hay habitaciones de tipo " + tipoHabitacion + " en estado " + estadoHabitacion);
      }

      return habitaciones.stream()
             .map(HabitacionMapper::toDTO)
             .collect(Collectors.toList());
}


    @Override
    public List<DTHabitacion> listarHabitaciones() {
        List<Habitacion> habitaciones = habitacionRepository.findAll();
        if (habitaciones.isEmpty()) {
            throw new EntityNotFoundException("No hay habitaciones registradas");
        }
        return habitaciones.stream()
                .map(HabitacionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DTHabitacion actualizarHabitacion(Long id, DTHabitacion dtHabitacion) {
        if (!habitacionRepository.existsById(id)) {
            throw new EntityNotFoundException("Habitación con ID " + id + " no existe");
        }

        try {
            Habitacion habitacion = HabitacionMapper.toEntity(dtHabitacion);
            habitacion.setId(id);
            Habitacion actualizada = habitacionRepository.save(habitacion);
            return HabitacionMapper.toDTO(actualizada);
        } catch (DataIntegrityViolationException e) {
            throw new PersistenceException("Error al actualizar la habitación: " + e.getMessage());
        }
    }

    @Override
    public void eliminarHabitacion(Long id) {
        if (!habitacionRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Habitacion con ID " + id + " no existe");
        }
        try {
            habitacionRepository.deleteById(id);
        } catch (Exception e) {
            throw new PersistenceException("Error al eliminar habitación: " + e.getMessage());
        }
    }

    @Override
    public void eliminarHabitacionPorNumero(String numero) {
        Habitacion habitacion = habitacionRepository.findByNumero(numero)
                .orElseThrow(() -> new EntityNotFoundException("No se puede eliminar. Habitacion con número " + numero + " no existe"));

        try {
            habitacionRepository.delete(habitacion);
        } catch (Exception e) {
            throw new PersistenceException("Error al eliminar habitación con número " + numero + ": " + e.getMessage());
        }
    }
}
