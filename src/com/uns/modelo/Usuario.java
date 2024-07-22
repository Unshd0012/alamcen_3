package com.uns.modelo;

public class Usuario {
    private int id;
    private String email;
    private String password;
    private int tipo;

    // Constructor
    public Usuario(int id, String email, String password, int tipo) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
