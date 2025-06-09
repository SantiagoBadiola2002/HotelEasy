package com.hotel.hoteleasy.controller;

import com.hotel.hoteleasy.dtos.DTUsuario;
import com.hotel.hoteleasy.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<DTUsuario> crearUsuario(@RequestBody DTUsuario usuarioDTO) {
        DTUsuario nuevoUsuario = usuarioService.crearUsuario(usuarioDTO);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTUsuario> actualizarUsuario(@PathVariable Long id, @RequestBody DTUsuario usuarioDTO) {
        try {
            DTUsuario actualizado = usuarioService.actualizarUsuario(id, usuarioDTO);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTUsuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<DTUsuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<DTUsuario>> listarUsuarios() {
        List<DTUsuario> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/por-nombreUsuario/{nombreUsuario}")
    public ResponseEntity<DTUsuario> obtenerPorNombreUsuario(@PathVariable String nombreUsuario) {
        Optional<DTUsuario> usuario = usuarioService.obtenerPorNombreUsuario(nombreUsuario);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
