package com.uns.controlador;

import com.uns.modelo.Producto;
import com.uns.modelo.ProductoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import javafx.scene.layout.BorderPane;

public class ProductosController {

    @FXML
    private TextField searchField;
     @FXML
    private Button usuariosButton;

    @FXML
    private Button buscarButton;

    @FXML
    private Button carritoButton;

    @FXML
    private GridPane productosGrid;
    
    private ProductoDAO productoDAO;
    private ObservableList<Producto> productosObservableList;

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
            productBox.setSpacing(10);
            productBox.getStyleClass().add("product-box");

            ImageView productImage = new ImageView(new Image("file:src/com/uns/res/img/box.png"));
            productImage.setFitWidth(100);
            productImage.setFitHeight(100);

            Label productDescription = new Label(producto.getNombre() + "\n" + producto.getDescripcion());
            productDescription.getStyleClass().add("product-description");

            Label productPrice = new Label("$ " + producto.getPrecio());
            productPrice.getStyleClass().add("product-price");

            Button viewButton = new Button("Ver");
            viewButton.getStyleClass().add("product-button");
            viewButton.setOnAction(e -> handleVerProducto(producto));

            productBox.getChildren().addAll(productImage, productDescription, productPrice, viewButton);
            productosGrid.add(productBox, column, row);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
    }

    private void handleVerProducto(Producto producto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uns/vista/producto_detalle.fxml"));
            VBox root = loader.load();

            ProductoDetalleController controller = loader.getController();
            controller.setProducto(producto);
            

            Scene scene = new Scene(root, 300, 400);
            scene.getStylesheets().add(getClass().getResource("/com/uns/res/css/producto_detalle.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Detalle del Producto");
            controller.setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML 
    private void handleUsuarios() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uns/vista/usuarios.fxml"));
            BorderPane root = loader.load();

            UsuariosController controller = loader.getController();
            

            Scene scene = new Scene(root, 300, 400);
            scene.getStylesheets().add(getClass().getResource("/com/uns/res/css/usuarios.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Detalle del Producto");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleCarrito() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uns/vista/carrito.fxml"));
           VBox root = loader.load();

            CarritoController controller = loader.getController();
           

            Scene scene = new Scene(root, 300, 400);
            scene.getStylesheets().add(getClass().getResource("/com/uns/res/css/carrito.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Detalle del Producto");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
