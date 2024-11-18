package com.academia.equipamentos.dto;

/**
 * DTO para representar os dados de um usuário.
 */
public class UserDTO {

    private Long id;          // Identificador único do usuário
    private String nome;      // Nome do usuário
    private String email;     // Email do usuário
    private String tipo;      // Tipo de usuário
    private String senha;     // Senha do usuário

    // Construtor padrão
    public UserDTO() {}

    // Construtor com argumentos
    public UserDTO(Long id, String nome, String email, String tipo, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
        this.senha = senha;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
