package com.academia.equipamentos.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

/**
 * Classe representando os equipamentos do sistema SAFEGYM.
 *
 * Esta classe define os atributos essenciais para o gerenciamento de equipamentos, incluindo:
 * - Nome do equipamento
 * - Descrição e localização
 * - Data de aquisição
 * - Status operacional (ATIVO, INATIVO, EM MANUTENCAO)
 * - Informações do fabricante e modelo
 * - Categoria do equipamento
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipamentos")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome; // Nome do equipamento

    @Column(columnDefinition = "TEXT")
    private String descricao; // Descrição detalhada do equipamento

    @Column(length = 100)
    private String localizacao; // Localização física do equipamento na academia

    @Column(name = "data_aquisicao")
    private LocalDate dataAquisicao; // Data de aquisição do equipamento

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusEquipamento status = StatusEquipamento.ATIVO; // Status do equipamento

    @Column(length = 100)
    private String fabricante; // Nome do fabricante do equipamento

    @Column(length = 100)
    private String modelo; // Modelo do equipamento

    @Column(length = 50)
    private String categoria; // Categoria do equipamento (exemplo: Cardio, Musculação)

    /**
     * Enum representando os possíveis status de um equipamento.
     */
    public enum StatusEquipamento {
        ATIVO, INATIVO, EM_MANUTENCAO
    }
}
