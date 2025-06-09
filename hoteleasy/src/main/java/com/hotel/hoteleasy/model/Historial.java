package com.hotel.hoteleasy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_checkin_checkout")
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    private LocalDateTime fechaCheckin;
    private LocalDateTime fechaCheckout;

    // Constructor sin parámetros
    public Historial() {
    }

    // Constructor con parámetros
    public Historial(Reserva reserva, LocalDateTime fechaCheckin, LocalDateTime fechaCheckout) {
        this.reserva = reserva;
        this.fechaCheckin = fechaCheckin;
        this.fechaCheckout = fechaCheckout;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public LocalDateTime getFechaCheckin() {
        return fechaCheckin;
    }

    public void setFechaCheckin(LocalDateTime fechaCheckin) {
        this.fechaCheckin = fechaCheckin;
    }

    public LocalDateTime getFechaCheckout() {
        return fechaCheckout;
    }

    public void setFechaCheckout(LocalDateTime fechaCheckout) {
        this.fechaCheckout = fechaCheckout;
    }


}
