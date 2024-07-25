package com.uns.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAO {

    // Método para crear un nuevo carrito
    public void crearCarrito(Carrito carrito) {
        String sql = "INSERT INTO carrito (id_orden, id_producto, cantidad, id_usuario) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, carrito.getIdOrden());
            pstmt.setInt(2, carrito.getIdProducto());
            pstmt.setInt(3, carrito.getCantidad());
            pstmt.setInt(4, carrito.getIdUsuario());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear carrito: " + e.getMessage());
        }
    }

    // Método para obtener un carrito por su ID
    public Carrito obtenerCarritoPorId(int id) {
        String sql = "SELECT * FROM carrito WHERE id = ?";
        Carrito carrito = null;

        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                carrito = new Carrito(
                    rs.getInt("id"),
                    rs.getInt("id_orden"),
                    rs.getInt("id_producto"),
                    rs.getInt("cantidad"),
                    rs.getInt("id_usuario")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener carrito: " + e.getMessage());
        }

        return carrito;
    }

    // Método para obtener todos los carritos
    public List<Carrito> obtenerTodosLosCarritos() {
        String sql = "SELECT * FROM carrito";
        List<Carrito> carritos = new ArrayList<>();

        try (Connection conn = Conexion.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Carrito carrito = new Carrito(
                    rs.getInt("id"),
                    rs.getInt("id_orden"),
                    rs.getInt("id_producto"),
                    rs.getInt("cantidad"),
                    rs.getInt("id_usuario")
                );
                carritos.add(carrito);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener carritos: " + e.getMessage());
        }

        return carritos;
    }

    // Método para actualizar un carrito
    public void actualizarCarrito(Carrito carrito) {
        String sql = "UPDATE carrito SET id_orden = ?, id_producto = ?, cantidad = ?, id_usuario = ? WHERE id = ?";

        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, carrito.getIdOrden());
            pstmt.setInt(2, carrito.getIdProducto());
            pstmt.setInt(3, carrito.getCantidad());
            pstmt.setInt(4, carrito.getIdUsuario());
            pstmt.setInt(5, carrito.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar carrito: " + e.getMessage());
        }
    }

    // Método para eliminar un carrito
    public void eliminarCarrito(int id) {
        String sql = "DELETE FROM carrito WHERE id = ?";

        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar carrito: " + e.getMessage());
        }
    }
    
    public List<Carrito> obtenerTodosLosItemsDelCarrito() {
    String sql = "SELECT * FROM carrito";
    List<Carrito> itemsCarrito = new ArrayList<>();

    try (Connection conn = Conexion.getInstance().getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            Carrito item = new Carrito(
                rs.getInt("id"),
                rs.getInt("id_orden"),
                rs.getInt("id_producto"),
                rs.getInt("cantidad"),
                rs.getInt("id_usuario")
            );
            itemsCarrito.add(item);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener items del carrito: " + e.getMessage());
    }

    return itemsCarrito;
}

public void actualizarItemDelCarrito(Carrito item) {
    String sql = "UPDATE carrito SET cantidad = ? WHERE id = ?";

    try (Connection conn = Conexion.getInstance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, item.getCantidad());
        pstmt.setInt(2, item.getId());
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al actualizar item del carrito: " + e.getMessage());
    }
}

public void crearItemDelCarrito(Carrito item) {
    String sql = "INSERT INTO carrito (id_orden, id_producto, cantidad, id_usuario) VALUES (?, ?, ?, ?)";

    try (Connection conn = Conexion.getInstance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, item.getIdOrden());
        pstmt.setInt(2, item.getIdProducto());
        pstmt.setInt(3, item.getCantidad());
        pstmt.setInt(4, item.getIdUsuario());
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al crear item del carrito: " + e.getMessage());
    }
}


}
