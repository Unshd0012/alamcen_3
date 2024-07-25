package com.uns.controlador;

import com.uns.modelo.Carrito;
import com.uns.modelo.CarritoDAO;
import com.uns.modelo.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ProductoDetalleController {

    @FXML
    private ImageView productoImagen;

    @FXML
    private Label productoNombre;

    @FXML
    private Label productoDescripcion;

    @FXML
    private Label productoPrecio;

    @FXML
    private Button addCartButton;

    private Producto producto;
    private CarritoDAO carritoDAO;

    public ProductoDetalleController() {
        carritoDAO = new CarritoDAO();
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        productoNombre.setText(producto.getNombre());
        productoDescripcion.setText(producto.getDescripcion());
        productoPrecio.setText("$ " + producto.getPrecio());
        productoImagen.setImage(new Image("file:src/com/uns/res/img/box.png"));
    }

    @FXML
    private void handleAddCart() {
        Carrito itemCarrito = new Carrito(0, 1, producto.getId(), 1, 1); // ID del usuario a ajustar según sea necesario
        carritoDAO.crearItemDelCarrito(itemCarrito);
        ((Stage) addCartButton.getScene().getWindow()).close();
    }
}
