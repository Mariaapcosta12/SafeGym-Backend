package com.academia.equipamentos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Classe representando um usuário no sistema SAFEGYM.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username; // Nome de usuário único para login

    @Column(nullable = false, unique = true, length = 100)
    private String email; // E-mail único do usuário

    @Column(nullable = false)
    private String senha; // Senha do usuário (armazenada encriptada)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoUsuario tipo = TipoUsuario.USUARIO; // Tipo de usuário (ADMIN, USUARIO), padrão USUARIO

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.ATIVO; // Status do usuário (ATIVO, INATIVO), padrão ATIVO

    @Column(name = "nome_completo", nullable = false, length = 150)
    private String nomeCompleto; // Nome completo do usuário

    @Column(name = "telefone", length = 15)
    private String telefone; // Telefone opcional do usuário

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao; // Data de criação gerada automaticamente

    /**
     * Verifica se o usuário está ativo no sistema.
     *
     * @return true se o status for ATIVO, false caso contrário.
     */
    public boolean isAtivo() {
        return Status.ATIVO.equals(this.status);
    }
}
