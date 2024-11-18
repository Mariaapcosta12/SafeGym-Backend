package com.academia.equipamentos.service;

import com.academia.equipamentos.model.Equipamento;
import com.academia.equipamentos.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pelas operações relacionadas à entidade Equipamento.
 *
 * Este serviço centraliza a lógica de negócios para o gerenciamento de equipamentos, incluindo:
 * - Listagem de todos os equipamentos.
 * - Registro e atualização de informações dos equipamentos.
 * - Remoção de equipamentos.
 * - Busca de equipamentos por ID.
 */
@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    /**
     * Lista todos os equipamentos cadastrados.
     *
     * @return Lista de todos os equipamentos.
     */
    public List<Equipamento> listAll() {
        return equipmentRepository.findAll();
    }

    /**
     * Salva um novo equipamento no banco de dados.
     *
     * @param equipamento Objeto contendo os dados do equipamento a ser salvo.
     * @return O equipamento salvo.
     */
    public Equipamento save(Equipamento equipamento) {
        return equipmentRepository.save(equipamento);
    }

    /**
     * Atualiza os dados de um equipamento existente.
     *
     * @param id          ID do equipamento a ser atualizado.
     * @param equipamento Objeto contendo os novos dados do equipamento.
     * @return O equipamento atualizado.
     * @throws RuntimeException Se o equipamento não for encontrado.
     */
    public Equipamento update(Long id, Equipamento equipamento) {
        Equipamento existing = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado com ID: " + id));

        existing.setNome(equipamento.getNome());
        existing.setDescricao(equipamento.getDescricao());
        existing.setLocalizacao(equipamento.getLocalizacao());
        existing.setDataAquisicao(equipamento.getDataAquisicao());
        existing.setStatus(equipamento.getStatus());
        existing.setFabricante(equipamento.getFabricante());
        existing.setModelo(equipamento.getModelo());
        existing.setCategoria(equipamento.getCategoria());

        return equipmentRepository.save(existing);
    }

    /**
     * Remove um equipamento do banco de dados.
     *
     * @param id ID do equipamento a ser removido.
     * @throws RuntimeException Se o equipamento não for encontrado.
     */
    public void delete(Long id) {
        if (!equipmentRepository.existsById(id)) {
            throw new RuntimeException("Equipamento não encontrado com ID: " + id);
        }
        equipmentRepository.deleteById(id);
    }

    /**
     * Busca um equipamento pelo ID.
     *
     * @param id ID do equipamento.
     * @return Um Optional contendo o equipamento, se encontrado.
     */
    public Optional<Equipamento> findById(Long id) {
        return equipmentRepository.findById(id);
    }

    /**
     * Busca equipamentos por categoria.
     *
     * @param categoria Categoria do equipamento.
     * @return Lista de equipamentos pertencentes à categoria especificada.
     */
    public List<Equipamento> findByCategoria(String categoria) {
        // Método personalizado pode ser adicionado ao repository.
        return equipmentRepository.findAll(); // Placeholder para implementação futura.
    }
}
