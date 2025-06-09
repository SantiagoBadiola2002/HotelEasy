package com.hotel.hoteleasy.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

import com.hotel.hoteleasy.enums.*;

@Entity
@Table(name = "habitaciones")
@Data // Incluye getters, setters, toString, equals y hashCode
@NoArgsConstructor // Constructor sin parámetros
@AllArgsConstructor // Constructor con todos los parámetros
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numero;

    @Enumerated(EnumType.STRING)
    private TipoHabitacion tipo;

    @Enumerated(EnumType.STRING)
    private EstadoHabitacion estado;

    private String descripcion;
    
    private BigDecimal precioPorNoche;
    
    private int numeroDePiso;

    @OneToMany(mappedBy = "habitacion")
    private List<ReservaHabitacion> reservasHabitacion;

}
