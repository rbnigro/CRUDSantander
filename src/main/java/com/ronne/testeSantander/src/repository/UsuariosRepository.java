package com.ronne.testeSantander.src.repository;


import com.ronne.testeSantander.src.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
}
