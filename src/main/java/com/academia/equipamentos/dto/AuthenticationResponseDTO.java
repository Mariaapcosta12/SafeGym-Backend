package com.academia.equipamentos.dto;

/**
 * DTO para resposta de autenticação contendo o token JWT, email do usuário e nome completo.
 */
public class AuthenticationResponseDTO {
    private String token;
    private String email;
    private String nomeCompleto;

    /**
     * Construtor com todos os campos.
     *
     * @param token       Token JWT gerado para o usuário.
     * @param email       Email do usuário autenticado.
     * @param nomeCompleto Nome completo do usuário autenticado.
     */
    public AuthenticationResponseDTO(String token, String email, String nomeCompleto) {
        this.token = token;
        this.email = email;
        this.nomeCompleto = nomeCompleto;
    }

    // Getters e Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
}
