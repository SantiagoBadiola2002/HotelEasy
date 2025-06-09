package com.hotel.hoteleasy.serviceimpl;

import com.hotel.hoteleasy.config.*;

import com.hotel.hoteleasy.dtos.DTUsuario;
import com.hotel.hoteleasy.mapped.UsuarioMapper;
import com.hotel.hoteleasy.model.Usuario;
import com.hotel.hoteleasy.repository.UsuarioRepository;
import com.hotel.hoteleasy.service.UsuarioService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public DTUsuario crearUsuario(DTUsuario usuarioDTO) {
        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
        
        // Hashear la contraseña antes de guardar
        String hashedPassword = passwordEncoder.encode(usuarioDTO.getPasswordHash());
        usuario.setPasswordHash(hashedPassword);

        Usuario guardado = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(guardado);
    }

    @Override
    public DTUsuario actualizarUsuario(Long id, DTUsuario usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setNombreCompleto(usuarioDTO.getNombreCompleto());
        usuario.setActivo(usuarioDTO.isActivo());

        // Hashear la contraseña (opcional: podrías verificar si ha cambiado)
        String hashedPassword = passwordEncoder.encode(usuarioDTO.getPasswordHash());
        usuario.setPasswordHash(hashedPassword);

        Usuario actualizado = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(actualizado);
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<DTUsuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toDTO);
    }

    @Override
    public List<DTUsuario> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DTUsuario> obtenerPorNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario)
                .map(UsuarioMapper::toDTO);
    }
}
