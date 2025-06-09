package com.hotel.hoteleasy.repository;

import com.hotel.hoteleasy.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aquí puedes agregar métodos personalizados si los necesitas
     Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
