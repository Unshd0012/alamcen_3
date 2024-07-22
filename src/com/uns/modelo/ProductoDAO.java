package com.uns.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // Método para crear un nuevo producto
    public void crearProducto(Producto producto) {
        String sql = "INSERT INTO productos (codigo, nombre, descripcion, precio) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, producto.getCodigo());
            pstmt.setString(2, producto.getNombre());
            pstmt.setString(3, producto.getDescripcion());
            pstmt.setDouble(4, producto.getPrecio());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear producto: " + e.getMessage());
        }
    }

    // Método para obtener un producto por su ID
    public Producto obtenerProductoPorId(int id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        Producto producto = null;

        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                producto = new Producto(
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener producto: " + e.getMessage());
        }

        return producto;
    }

    // Método para obtener todos los productos
    public List<Producto> obtenerTodosLosProductos() {
        String sql = "SELECT * FROM productos";
        List<Producto> productos = new ArrayList<>();

        try (Connection conn = Conexion.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto producto = new Producto(
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener productos: " + e.getMessage());
        }

        return productos;
    }

    // Método para actualizar un producto
    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET codigo = ?, nombre = ?, descripcion = ?, precio = ? WHERE id = ?";

        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, producto.getCodigo());
            pstmt.setString(2, producto.getNombre());
            pstmt.setString(3, producto.getDescripcion());
            pstmt.setDouble(4, producto.getPrecio());
            pstmt.setInt(5, producto.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    // Método para eliminar un producto
    public void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }
}
