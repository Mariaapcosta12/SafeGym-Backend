package com.academia.equipamentos.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * DTO para receber as credenciais de login do cliente.
 */
public class LoginRequest {
    @NotEmpty(message = "O campo 'username' é obrigatório.")
    @Size(min = 3, max = 50, message = "O campo 'username' deve ter entre 3 e 50 caracteres.")
    private String username;

    @NotEmpty(message = "O campo 'password' é obrigatório.")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
    private String password;

    // Construtor padrão
    public LoginRequest() {}

    // Construtor com todos os campos
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters e setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Sobrescrevendo toString para evitar exibir informações sensíveis
    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
}
