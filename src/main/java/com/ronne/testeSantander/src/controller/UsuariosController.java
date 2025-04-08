package com.ronne.testeSantander.src.controller;

import com.ronne.testeSantander.src.dto.UsuarioDTO;
import com.ronne.testeSantander.src.entity.Usuarios;
import com.ronne.testeSantander.src.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuarioService service;

    public UsuariosController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Usuarios>> listarUsuarios() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/lote")
    public ResponseEntity<List<Usuarios>> salvarEmLote(@RequestBody @Valid List<UsuarioDTO> usuarios) {
        List<Usuarios> salvos = service.salvarEmLote(usuarios);
        return ResponseEntity.ok(salvos);
    }

    @PostMapping
    public ResponseEntity<Usuarios> adicionarUsuario(@RequestBody UsuarioDTO usuariosDTO) {
        Usuarios usuarioSalvo = service.salvar(usuariosDTO);
        return ResponseEntity.ok(usuarioSalvo);
    }
}
