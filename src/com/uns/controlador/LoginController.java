package com.uns.controlador;

import com.uns.modelo.UsuarioDAO;
import com.uns.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    public void handleLoginButtonAction(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        Usuario usuario = usuarioDAO.obtenerUsuarioPorEmailYPassword(email, password);

        if (usuario != null) {
            errorLabel.setText("Login successful!");
            // Aquí puedes redirigir a la ventana principal o home
        } else {
            errorLabel.setText("Invalid email or password!");
        }
    }
}
