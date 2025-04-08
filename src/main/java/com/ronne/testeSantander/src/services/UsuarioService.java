package com.ronne.testeSantander.src.services;

import com.ronne.testeSantander.src.dto.UsuarioDTO;
import com.ronne.testeSantander.src.entity.Usuarios;
import com.ronne.testeSantander.src.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuariosRepository usuariosRepository;

    public UsuarioService(UsuariosRepository repository) {
        this.usuariosRepository = repository;
    }

    public List<Usuarios> listarTodos() {
        return usuariosRepository.findAll();
    }

    public Usuarios salvar(UsuarioDTO usuariosDTO) {
        Usuarios usuario = new Usuarios();
        usuario.setNome(usuariosDTO.getNome());
        usuario.setEmail(usuariosDTO.getEmail());

        return usuariosRepository.save(usuario);
    }

    public Optional<Usuarios> buscarPorId(Long id) {
        return usuariosRepository.findById(id);
    }

    public List<Usuarios> salvarEmLote(List<UsuarioDTO> dtos) {
        List<Usuarios> usuarios = dtos.stream().map(dto -> {
            Usuarios usuariosInsert = new Usuarios();
            usuariosInsert.setNome(dto.getNome());
            usuariosInsert.setEmail(dto.getEmail());
            return usuariosInsert;
        }).collect(Collectors.toList());

        return usuariosRepository.saveAll(usuarios);
    }

    public boolean deletarPorId(Long id) {
        if (usuariosRepository.existsById(id)) {
            usuariosRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
