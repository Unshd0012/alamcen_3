package com.uns.controlador;

import com.uns.modelo.Producto;
import com.uns.modelo.ProductoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

import java.util.List;

public class ProductosController {

    @FXML
    private TextField searchField;

    @FXML
    private GridPane productosGrid;

    private ProductoDAO productoDAO;

    public ProductosController() {
        productoDAO = new ProductoDAO();
    }

    @FXML
    public void initialize() {
        cargarProductos();
    }

    @FXML
    private void handleBuscarProducto() {
        String descripcion = searchField.getText();
        cargarProductos(descripcion);
    }

    private void cargarProductos() {
        cargarProductos("");
    }

    private void cargarProductos(String descripcion) {
        productosGrid.getChildren().clear();
        List<Producto> productos = productoDAO.obtenerProductosPorDescripcion(descripcion);
        int column = 0;
        int row = 0;

        for (Producto producto : productos) {
            VBox productBox = new VBox();
            productBox.getStyleClass().add("product-box");

            ImageView productImage = new ImageView(new Image("file:src/com/uns/res/img/product.png")); // Ruta a la imagen del producto
            productImage.getStyleClass().add("product-image");

            Label productDescription = new Label(producto.getNombre() + "\n" + producto.getDescripcion());
            productDescription.getStyleClass().add("product-description");

            Label productPrice = new Label("$ " + producto.getPrecio());
            productPrice.getStyleClass().add("product-price");

            HBox buttonsBox = new HBox();
            buttonsBox.getStyleClass().add("product-buttons");

            Button viewButton = new Button("Ver");
            viewButton.getStyleClass().add("product-button");

            Button addButton = new Button("Agregar");
            addButton.getStyleClass().add("product-button");

            buttonsBox.getChildren().addAll(viewButton, addButton);

            productBox.getChildren().addAll(productImage, productDescription, productPrice, buttonsBox);
            productosGrid.add(productBox, column, row);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
    }
}
