package com.academia.equipamentos.controller;

import com.academia.equipamentos.dto.EquipmentDTO;
import com.academia.equipamentos.model.Equipamento;
import com.academia.equipamentos.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para operações relacionadas à entidade Equipamento.
 *
 * Este controlador gerencia as operações CRUD (Create, Read, Update, Delete) para equipamentos,
 * incluindo a criação, atualização, exclusão e listagem de equipamentos.
 *
 * Endpoints principais:
 * - GET /api/equipments: Retorna todos os equipamentos.
 * - POST /api/equipments: Cria um novo equipamento.
 * - PUT /api/equipments/{id}: Atualiza os dados de um equipamento existente.
 * - DELETE /api/equipments/{id}: Exclui um equipamento pelo ID.
 */
@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    /**
     * Retorna todos os equipamentos cadastrados.
     *
     * @return ResponseEntity contendo a lista de equipamentos.
     */
    @GetMapping
    public ResponseEntity<List<Equipamento>> getAllEquipments() {
        List<Equipamento> equipments = equipmentService.listAll();
        return ResponseEntity.ok(equipments);
    }

    /**
     * Cria um novo equipamento com base nos dados fornecidos no DTO.
     *
     * @param equipmentDTO Dados do equipamento a ser criado.
     * @return ResponseEntity contendo o equipamento criado.
     */
    @PostMapping
    public ResponseEntity<Equipamento> createEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        Equipamento equipamento = convertDtoToEntity(equipmentDTO);
        Equipamento createdEquipment = equipmentService.save(equipamento);
        return ResponseEntity.ok(createdEquipment);
    }

    /**
     * Atualiza um equipamento existente pelo ID.
     *
     * @param id           ID do equipamento a ser atualizado.
     * @param equipmentDTO Dados atualizados do equipamento.
     * @return ResponseEntity contendo o equipamento atualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> updateEquipment(
            @PathVariable Long id, @RequestBody EquipmentDTO equipmentDTO) {
        Equipamento equipamento = convertDtoToEntity(equipmentDTO);
        Equipamento updatedEquipment = equipmentService.update(id, equipamento);
        return ResponseEntity.ok(updatedEquipment);
    }

    /**
     * Exclui um equipamento existente pelo ID.
     *
     * @param id ID do equipamento a ser excluído.
     * @return ResponseEntity com status 204 (No Content) em caso de sucesso.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        equipmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Converte um EquipmentDTO em uma entidade Equipamento.
     *
     * Este método auxilia na conversão de um DTO em um modelo de domínio
     * que pode ser persistido no banco de dados.
     *
     * @param equipmentDTO Dados do equipamento no formato DTO.
     * @return Entidade Equipamento.
     */
    private Equipamento convertDtoToEntity(EquipmentDTO equipmentDTO) {
        Equipamento equipamento = new Equipamento();

        // Define os valores básicos do equipamento
        equipamento.setNome(equipmentDTO.getNome());
        equipamento.setDescricao(equipmentDTO.getDescricao());
        equipamento.setLocalizacao(equipmentDTO.getLocalizacao());
        equipamento.setDataAquisicao(equipmentDTO.getDataAquisicao());
        equipamento.setFabricante(equipmentDTO.getFabricante());
        equipamento.setModelo(equipmentDTO.getModelo());

        // Converte o status de String para o Enum correspondente
        if (equipmentDTO.getStatus() != null) {
            try {
                equipamento.setStatus(Equipamento.StatusEquipamento.valueOf(
                        equipmentDTO.getStatus().toUpperCase() // Converte para maiúsculas
                ));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Status inválido: " + equipmentDTO.getStatus());
            }
        }

        // Define a categoria do equipamento
        equipamento.setCategoria(equipmentDTO.getCategoria());

        return equipamento;
    }
}
