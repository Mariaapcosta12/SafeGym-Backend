package com.academia.equipamentos.dto;

import java.time.LocalDate;

/**
 * DTO para representar os dados de um equipamento.
 * Inclui informações como nome, descrição, localização e outros detalhes relevantes.
 */
public class EquipmentDTO {

    private Long id; // Identificador único do equipamento
    private String nome; // Nome do equipamento
    private String descricao; // Descrição detalhada do equipamento
    private String localizacao; // Local onde o equipamento está instalado
    private String fabricante; // Fabricante do equipamento
    private String modelo; // Modelo do equipamento
    private String status; // Status atual do equipamento (ex: Ativo, Inativo, Manutenção)
    private String categoria; // Categoria à qual o equipamento pertence
    private LocalDate dataAquisicao; // Data de aquisição do equipamento

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    @Override
    public String toString() {
        return "EquipmentDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", modelo='" + modelo + '\'' +
                ", status='" + status + '\'' +
                ", categoria='" + categoria + '\'' +
                ", dataAquisicao=" + dataAquisicao +
                '}';
    }
}
