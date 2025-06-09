package com.hotel.hoteleasy.repository;

import com.hotel.hoteleasy.model.ReservaHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaHabitacionRepository extends JpaRepository<ReservaHabitacion, Long> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}
