package com.uns.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Método para crear un nuevo usuario
    public void crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (email, password, tipo) VALUES (?, ?, ?)";

        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getEmail());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setInt(3, usuario.getTipo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e.getMessage());
        }
    }

    // Método para obtener un usuario por su ID
    public Usuario obtenerUsuarioPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario usuario = null;

        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getInt("tipo")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuario: " + e.getMessage());
        }

        return usuario;
    }

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = Conexion.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getInt("tipo")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios: " + e.getMessage());
        }

        return usuarios;
    }

    // Método para actualizar un usuario
    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET email = ?, password = ?, tipo = ? WHERE id = ?";

        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getEmail());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setInt(3, usuario.getTipo());
            pstmt.setInt(4, usuario.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
    }
    
    public Usuario obtenerUsuarioPorEmailYPassword(String email, String password) {
    String sql = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
    Usuario usuario = null;

    try (Connection conn = Conexion.getInstance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, email);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            usuario = new Usuario(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("tipo")
            );
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener usuario: " + e.getMessage());
    }

    return usuario;
}

}
