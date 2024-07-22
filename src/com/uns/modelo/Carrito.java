package com.uns.modelo;

public class Carrito {
    private int id;
    private int idOrden;
    private int idProducto;
    private int cantidad;
    private int idUsuario;

    // Constructor
    public Carrito(int id, int idOrden, int idProducto, int cantidad, int idUsuario) {
        this.id = id;
        this.idOrden = idOrden;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.idUsuario = idUsuario;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
