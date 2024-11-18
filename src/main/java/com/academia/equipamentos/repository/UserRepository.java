package com.academia.equipamentos.repository;

import com.academia.equipamentos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface de repositório para a entidade Usuario.
 *
 * Esta interface fornece métodos de acesso ao banco de dados relacionados à entidade Usuario,
 * permitindo operações CRUD e buscas personalizadas.
 */
@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca um usuário pelo nome de usuário (username).
     *
     * @param username Nome de usuário do sistema.
     * @return Um Optional contendo o usuário, se encontrado.
     */
    Optional<Usuario> findByUsername(String username);

    /**
     * Busca um usuário pelo e-mail.
     *
     * @param email E-mail do usuário.
     * @return Um Optional contendo o usuário, se encontrado.
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Verifica se existe um usuário com o e-mail fornecido.
     *
     * @param email E-mail do usuário.
     * @return True se o usuário existir, false caso contrário.
     */
    boolean existsByEmail(String email);

    /**
     * Verifica se existe um usuário com o nome de usuário fornecido.
     *
     * @param username Nome de usuário.
     * @return True se o usuário existir, false caso contrário.
     */
    boolean existsByUsername(String username);

    /**
     * Busca um usuário pelo nome de usuário e status.
     *
     * @param username Nome de usuário.
     * @param status   Status do usuário (ex.: "ATIVO", "INATIVO").
     * @return Um Optional contendo o usuário, se encontrado.
     */
    Optional<Usuario> findByUsernameAndStatus(String username, String status);
}
