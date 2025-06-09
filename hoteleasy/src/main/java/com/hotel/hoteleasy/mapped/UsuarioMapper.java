package com.hotel.hoteleasy.mapped;

import com.hotel.hoteleasy.model.Usuario;
import com.hotel.hoteleasy.dtos.DTUsuario;

public class UsuarioMapper {

    public static DTUsuario toDTO(Usuario usuario) {
        if (usuario == null) return null;

        return new DTUsuario(
            usuario.getId(),
            usuario.getNombreUsuario(),
            usuario.getPasswordHash(),
            usuario.getNombreCompleto(),
            usuario.isActivo()
        );
    }

    public static Usuario toEntity(DTUsuario dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();

        usuario.setId(dto.getId());
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setPasswordHash(dto.getPasswordHash());
        usuario.setNombreCompleto(dto.getNombreCompleto());
        usuario.setActivo(dto.isActivo());

        return usuario;
    }
}
