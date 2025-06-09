package com.hotel.hoteleasy.model;

import com.hotel.hoteleasy.enums.EstadoReserva;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vendedor_id") 
    private Usuario vendedor;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDateTime fechaReserva;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ReservaHabitacion> habitaciones = new ArrayList<>();

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Pago> pagos = new ArrayList<>();

    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private Historial historial;
}

