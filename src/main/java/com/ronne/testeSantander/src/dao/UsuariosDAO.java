package com.ronne.testeSantander.src.dao;


import com.ronne.testeSantander.src.entity.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {

    @Autowired
    private DataSource dataSource; // O Spring injeta o DataSource configurado automaticamente.

    public List<Usuarios> listarUsuarios() {
        String sql = "SELECT * FROM usuario";
        List<Usuarios> usuarios = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setId(resultSet.getLong("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setEmail(resultSet.getString("email"));
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    // Exemplo de método para salvar um usuário no banco
    public void salvarUsuario(Usuarios usuario) {
        String sql = "INSERT INTO usuario (nome, email) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
