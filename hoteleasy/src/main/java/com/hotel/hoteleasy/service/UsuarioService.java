package com.hotel.hoteleasy.service;

import com.hotel.hoteleasy.dtos.DTUsuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    DTUsuario crearUsuario(DTUsuario usuarioDTO);
    DTUsuario actualizarUsuario(Long id, DTUsuario usuarioDTO);
    void eliminarUsuario(Long id);
    Optional<DTUsuario> obtenerUsuarioPorId(Long id);
    List<DTUsuario> listarUsuarios();
    Optional<DTUsuario> obtenerPorNombreUsuario(String nombreUsuario);
}
