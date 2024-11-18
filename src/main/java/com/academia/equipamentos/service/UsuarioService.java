package com.academia.equipamentos.service;

import com.academia.equipamentos.model.Usuario;
import com.academia.equipamentos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pelas operações relacionadas à entidade Usuario.
 *
 * Este serviço centraliza a lógica de negócios para gerenciamento de usuários,
 * incluindo registro, atualização, exclusão, e validação de credenciais.
 *
 * Principais funcionalidades:
 * - Registro de novos usuários com verificação de e-mail único.
 * - Busca de usuários por ID ou listagem de todos os usuários.
 * - Atualização e exclusão de registros de usuários.
 * - Validação de credenciais para autenticação.
 */
@Service
public class UsuarioService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registra um novo usuário no sistema.
     *
     * @param user Objeto do usuário a ser registrado.
     * @return true se o registro for bem-sucedido, false se o usuário já existir.
     */
    public boolean registerUser(Usuario user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return false; // Usuário já existe com o mesmo email
        }
        user.setSenha(passwordEncoder.encode(user.getSenha())); // Codifica a senha
        userRepository.save(user);
        return true;
    }

    /**
     * Busca todos os usuários cadastrados.
     *
     * @return Lista de todos os usuários.
     */
    public List<Usuario> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Busca um usuário pelo ID.
     *
     * @param id ID do usuário.
     * @return Um Optional contendo o usuário, caso exista.
     */
    public Optional<Usuario> findUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Atualiza os dados de um usuário existente.
     *
     * @param user Objeto do usuário com os dados atualizados.
     * @return O usuário atualizado.
     * @throws RuntimeException Se o usuário não for encontrado.
     */
    public Usuario updateUser(Usuario user) {
        if (userRepository.existsById(user.getId())) {
            if (user.getSenha() != null && !user.getSenha().isEmpty()) {
                user.setSenha(passwordEncoder.encode(user.getSenha())); // Atualiza a senha codificada
            } else {
                // Caso a senha não seja fornecida, mantém a senha atual
                Usuario existingUser = userRepository.findById(user.getId()).orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado com ID: " + user.getId()));
                user.setSenha(existingUser.getSenha());
            }
            return userRepository.save(user);
        }
        throw new RuntimeException("Usuário não encontrado com ID: " + user.getId());
    }

    /**
     * Remove um usuário pelo ID.
     *
     * @param id ID do usuário a ser removido.
     * @throws RuntimeException Se o usuário não for encontrado.
     */
    public void deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
    }

    /**
     * Verifica se as credenciais do usuário são válidas.
     *
     * @param email    Email do usuário.
     * @param password Senha do usuário.
     * @return true se as credenciais forem válidas, false caso contrário.
     */
    public boolean validateUserCredentials(String email, String password) {
        Optional<Usuario> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();
            return passwordEncoder.matches(password, user.getSenha());
        }
        return false;
    }
}
