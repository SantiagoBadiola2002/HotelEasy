package com.hotel.hoteleasy.serviceimpl;

import com.hotel.hoteleasy.model.Pago;
import com.hotel.hoteleasy.model.Reserva;
import com.hotel.hoteleasy.dtos.DTPago;
import com.hotel.hoteleasy.enums.EstadoReserva;
import com.hotel.hoteleasy.repository.PagoRepository;
import com.hotel.hoteleasy.repository.ReservaRepository;
import com.hotel.hoteleasy.service.PagoService;
import com.hotel.hoteleasy.mapped.PagoMapper;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoServiceImpl implements PagoService {
    
    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private ReservaRepository reservaRepository;
    
    @Override
    public DTPago crearPago(DTPago pagoDTO) {
    Pago pago = PagoMapper.toEntity(pagoDTO);

    Long reservaId = pagoDTO.getReservaId();
    Reserva reserva = reservaRepository.findById(reservaId)
            .orElseThrow(() -> new EntityNotFoundException("Reserva con ID " + reservaId + " no encontrada"));

    pago.setReserva(reserva);

    Pago pagoGuardado = pagoRepository.save(pago);
    
    // Cambiar estado de la reserva a COMPLETADA si no lo está
    if (reserva.getEstado() != EstadoReserva.COMPLETADA) {
        reserva.setEstado(EstadoReserva.COMPLETADA);
        reservaRepository.save(reserva);
    }
    

    return PagoMapper.toDTO(pagoGuardado);
}

    
    @Override
    public DTPago obtenerPagoPorId(Long id) {
        return pagoRepository.findById(id)
                .map(PagoMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Pago con ID " + id + " no encontrado"));
    }
    
    @Override
    public List<DTPago> obtenerPagos() {
        List<Pago> pagos = pagoRepository.findAll();
        if (pagos.isEmpty()) {
            throw new EntityNotFoundException("No hay pagos registrados");
        }
        return pagos.stream()
                .map(PagoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<DTPago> obtenerPagosPorReservaId(Long reservaId) {
        List<Pago> pagos =  pagoRepository.findByReservaId(reservaId);
        if (pagos.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron pagos para la reserva con ID: " + reservaId);
        }
        return pagos.stream()
                .map(PagoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public void eliminarPago(Long id) {
        if (!pagoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pago con ID " + id + " no encontrado");
        }
        pagoRepository.deleteById(id);
    }
    
}
