package com.uns.controlador;

import com.uns.modelo.UsuarioDAO;
import com.uns.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private UsuarioDAO usuarioDAO;

    public LoginController() {
        usuarioDAO = new UsuarioDAO();
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        Usuario usuario = usuarioDAO.obtenerUsuarioPorEmailYPassword(email, password);

        if (usuario != null) {
            errorLabel.setText("Login successful!");
            abrirVentanaProductos();
            // Cerrar la ventana de login
            ((Stage) emailField.getScene().getWindow()).close();
        } else {
            errorLabel.setText("Invalid email or password!");
        }
    }

    private void abrirVentanaProductos() {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uns/vista/productos.fxml"));
            BorderPane root = loader.load();
            
            // Crear la escena
            Scene scene = new Scene(root, 800, 800);
         
            scene.getStylesheets().add(getClass().getResource("/com/uns/res/css/productos.css").toExternalForm());
            
            // Establecer la escena en un nuevo escenario
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/uns/res/img/box.png")));
            stage.setScene(scene);
            stage.setTitle("Productos Disponibles");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
