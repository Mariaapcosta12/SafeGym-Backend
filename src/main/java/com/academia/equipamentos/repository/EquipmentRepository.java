package com.academia.equipamentos.repository;

import com.academia.equipamentos.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para a entidade Equipamento.
 *
 * Esta interface fornece métodos de acesso ao banco de dados relacionados à entidade Equipamento,
 * permitindo operações CRUD (Create, Read, Update, Delete) e consultas adicionais.
 *
 * Métodos personalizados podem ser adicionados conforme necessário para atender a requisitos específicos.
 */
@Repository
public interface EquipmentRepository extends JpaRepository<Equipamento, Long> {
}
