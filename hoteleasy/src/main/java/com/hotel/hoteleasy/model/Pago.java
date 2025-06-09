package com.hotel.hoteleasy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hotel.hoteleasy.enums.*;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    private LocalDateTime fechaPago;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    // Constructor sin parámetros
    public Pago() {
    }

    // Constructor con parámetros
    public Pago(BigDecimal monto, MetodoPago metodoPago, LocalDateTime fechaPago, Reserva reserva) {
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.reserva = reserva;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
