package com.academia.equipamentos.util;

import com.academia.equipamentos.dto.EquipmentDTO;
import com.academia.equipamentos.model.Equipamento;

public class DtoConverter {

    /**
     * Converte um objeto Equipamento para um EquipmentDTO.
     *
     * @param equipamento O objeto Equipamento a ser convertido.
     * @return O objeto EquipmentDTO correspondente.
     */
    public static EquipmentDTO toDto(Equipamento equipamento) {
        EquipmentDTO dto = new EquipmentDTO();
        dto.setId(equipamento.getId());
        dto.setNome(equipamento.getNome());
        dto.setDescricao(equipamento.getDescricao());
        dto.setLocalizacao(equipamento.getLocalizacao());
        dto.setFabricante(equipamento.getFabricante());
        dto.setModelo(equipamento.getModelo());
        dto.setStatus(equipamento.getStatus().toString());
        dto.setCategoria(equipamento.getCategoria());
        dto.setDataAquisicao(equipamento.getDataAquisicao());
        return dto;
    }

    /**
     * Converte um objeto EquipmentDTO para um Equipamento.
     *
     * @param dto O objeto EquipmentDTO a ser convertido.
     * @return O objeto Equipamento correspondente.
     */
    public static Equipamento toEntity(EquipmentDTO dto) {
        Equipamento equipamento = new Equipamento();
        equipamento.setId(dto.getId());
        equipamento.setNome(dto.getNome());
        equipamento.setDescricao(dto.getDescricao());
        equipamento.setLocalizacao(dto.getLocalizacao());
        equipamento.setFabricante(dto.getFabricante());
        equipamento.setModelo(dto.getModelo());
        equipamento.setStatus(Equipamento.StatusEquipamento.valueOf(dto.getStatus().toUpperCase()));
        equipamento.setCategoria(dto.getCategoria());
        equipamento.setDataAquisicao(dto.getDataAquisicao());
        return equipamento;
    }
}
