package com.ronne.testeSantander.src.controller;

import com.ronne.testeSantander.src.dto.UsuarioDTO;
import com.ronne.testeSantander.src.entity.Usuarios;
import com.ronne.testeSantander.src.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuarioService service;

    public UsuariosController(UsuarioService service) {
        this.service = service;
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Usuarios> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioDTO usuarioDTO) {
        return service.atualizar(id, usuarioDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Usuarios>> listarBasestatus() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Usuarios>> listarUsuarios() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/consulta/{id}")
    public ResponseEntity<Usuarios> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/incluir/lote")
    public ResponseEntity<List<Usuarios>> salvarEmLote(@RequestBody @Valid List<UsuarioDTO> usuarios) {
        List<Usuarios> salvos = service.salvarEmLote(usuarios);
        return ResponseEntity.ok(salvos);
    }

    @PostMapping("/incluir/")
    public ResponseEntity<Usuarios> adicionarUsuario(@RequestBody UsuarioDTO usuariosDTO) {
        Usuarios usuarioSalvo = service.salvar(usuariosDTO);
        return ResponseEntity.ok(usuarioSalvo);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deletarUsuario(@PathVariable Long id) {
        boolean deletado = service.deletarPorId(id);

        Map<String, String> resposta = new HashMap<>();
        if (deletado) {
            resposta.put("mensagem", "Usuário deletado com sucesso.");
            return ResponseEntity.ok(resposta);
        } else {
            resposta.put("mensagem", "Usuário não encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}
