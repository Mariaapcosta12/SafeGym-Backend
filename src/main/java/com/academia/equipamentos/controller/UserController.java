package com.academia.equipamentos.controller;

import com.academia.equipamentos.model.Usuario;
import com.academia.equipamentos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para operações relacionadas à entidade Usuario.
 *
 * Este controlador gerencia as operações CRUD (Create, Read, Update, Delete) para usuários,
 * como registro, atualização, remoção e busca por ID ou listagem de todos os usuários.
 *
 * Endpoints principais:
 * - GET /api/users: Retorna todos os usuários.
 * - GET /api/users/{id}: Retorna um usuário pelo ID.
 * - POST /api/users: Registra um novo usuário.
 * - PUT /api/users/{id}: Atualiza os dados de um usuário existente.
 * - DELETE /api/users/{id}: Remove um usuário pelo ID.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UsuarioService userService;

    /**
     * Retorna todos os usuários cadastrados no sistema.
     *
     * @return ResponseEntity contendo a lista de usuários.
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 se não houver usuários
        }
        return ResponseEntity.ok(users);
    }

    /**
     * Retorna um usuário pelo ID.
     *
     * @param id ID do usuário.
     * @return ResponseEntity contendo o usuário, caso exista, ou status 404.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Registra um novo usuário no sistema.
     *
     * @param user Objeto contendo os dados do usuário a ser registrado.
     * @return ResponseEntity com mensagem de sucesso ou erro.
     */
    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody Usuario user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("Usuário registrado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro ao registrar usuário: " + e.getMessage());
        }
    }

    /**
     * Atualiza os dados de um usuário existente.
     *
     * @param id   ID do usuário a ser atualizado.
     * @param user Objeto contendo os novos dados do usuário.
     * @return ResponseEntity com o usuário atualizado ou status 404.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody Usuario user) {
        return userService.findUserById(id)
                .map(existingUser -> {
                    user.setId(id); // Garante que o ID correto está sendo atualizado
                    Usuario updatedUser = userService.updateUser(user);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Remove um usuário pelo ID.
     *
     * @param id ID do usuário a ser removido.
     * @return ResponseEntity com mensagem de sucesso ou erro.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (userService.findUserById(id).isEmpty()) {
            // Use um builder explícito para o status e corpo da mensagem
            return ResponseEntity.status(404).body("Usuário não encontrado.");
        }
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("Usuário removido com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro ao remover usuário: " + e.getMessage());
        }
    }
}
