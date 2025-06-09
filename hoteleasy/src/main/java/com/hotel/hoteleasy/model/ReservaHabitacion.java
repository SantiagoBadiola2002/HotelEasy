package com.hotel.hoteleasy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "reserva_habitaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;

    private BigDecimal precioFinal;
}
