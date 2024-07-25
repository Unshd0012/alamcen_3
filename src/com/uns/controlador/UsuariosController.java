package com.uns.controlador;

import com.uns.modelo.Usuario;
import com.uns.modelo.UsuarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class UsuariosController {

    @FXML
    private TableView<Usuario> usuariosTable;

    @FXML
    private TableColumn<Usuario, Integer> idColumn;

    @FXML
    private TableColumn<Usuario, String> emailColumn;

    @FXML
    private TableColumn<Usuario, String> passwordColumn;

    @FXML
    private TableColumn<Usuario, Integer> tipoColumn;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField tipoField;

    private UsuarioDAO usuarioDAO;
    private ObservableList<Usuario> usuariosObservableList;

    public UsuariosController() {
        usuarioDAO = new UsuarioDAO();
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        cargarUsuarios();
        agregarListenerTabla();
    }

    private void cargarUsuarios() {
        usuariosObservableList = FXCollections.observableArrayList(usuarioDAO.obtenerTodosLosUsuarios());
        usuariosTable.setItems(usuariosObservableList);
    }

    private void agregarListenerTabla() {
        usuariosTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                rellenarCampos(usuariosTable.getSelectionModel().getSelectedItem());
            }
        });
    }

    private void rellenarCampos(Usuario usuario) {
        if (usuario != null) {
            emailField.setText(usuario.getEmail());
            passwordField.setText(usuario.getPassword());
            tipoField.setText(String.valueOf(usuario.getTipo()));
        }
    }

    @FXML
    private void handleAgregarUsuario() {
        String email = emailField.getText();
        String password = passwordField.getText();
        int tipo = Integer.parseInt(tipoField.getText());

        Usuario usuario = new Usuario(0, email, password, tipo);
        usuarioDAO.crearUsuario(usuario);
        cargarUsuarios();
        limpiarCampos();
    }

    @FXML
    private void handleActualizarUsuario() {
        Usuario usuarioSeleccionado = usuariosTable.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado != null) {
            usuarioSeleccionado.setEmail(emailField.getText());
            usuarioSeleccionado.setPassword(passwordField.getText());
            usuarioSeleccionado.setTipo(Integer.parseInt(tipoField.getText()));
            usuarioDAO.actualizarUsuario(usuarioSeleccionado);
            cargarUsuarios();
            limpiarCampos();
        }
    }

    @FXML
    private void handleEliminarUsuario() {
        Usuario usuarioSeleccionado = usuariosTable.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado != null) {
            usuarioDAO.eliminarUsuario(usuarioSeleccionado.getId());
            cargarUsuarios();
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        emailField.clear();
        passwordField.clear();
        tipoField.clear();
    }
}
