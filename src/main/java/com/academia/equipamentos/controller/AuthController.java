package com.academia.equipamentos.controller;

import com.academia.equipamentos.model.Usuario;
import com.academia.equipamentos.repository.UserRepository;
import com.academia.equipamentos.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador responsável pela autenticação e registro de usuários.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    /**
     * Construtor para injetar dependências.
     */
    public AuthController(UserRepository userRepository, JwtService jwtService,
                          AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Endpoint para registro de novos usuários.
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody Usuario user) {
        log.info("Tentativa de registro de usuário: {}", user.getUsername());

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            log.warn("Nome de usuário já está em uso: {}", user.getUsername());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(createResponse("message", "Nome de usuário já está em uso."));
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.warn("E-mail já está em uso: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(createResponse("message", "E-mail já está em uso."));
        }

        user.setSenha(passwordEncoder.encode(user.getSenha()));
        userRepository.save(user);

        log.info("Usuário registrado com sucesso: {}", user.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createResponse("message", "Usuário registrado com sucesso!"));
    }

    /**
     * Endpoint para autenticação de usuários.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtService.generateToken(authentication);

            log.info("Login bem-sucedido para username: {}", username);
            return ResponseEntity.ok(createResponse("token", token));
        } catch (BadCredentialsException e) {
            log.warn("Credenciais inválidas para username: {}", loginRequest.get("username"));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(createResponse("message", "Credenciais inválidas."));
        } catch (Exception e) {
            log.error("Erro inesperado durante o login", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createResponse("message", "Erro interno do servidor."));
        }
    }

    /**
     * Endpoint para validação de tokens JWT.
     */
    @GetMapping("/validate")
    public ResponseEntity<Map<String, String>> validateToken(@RequestParam String token) {
        try {
            if (jwtService.validateToken(token)) {
                String username = jwtService.extractUsername(token);
                log.info("Token válido para username: {}", username);
                Map<String, String> response = new HashMap<>();
                response.put("message", "Token válido.");
                response.put("username", username);
                return ResponseEntity.ok(response);
            } else {
                log.warn("Token inválido ou expirado.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(createResponse("message", "Token inválido ou expirado."));
            }
        } catch (Exception e) {
            log.error("Erro ao validar token", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createResponse("message", "Erro interno do servidor."));
        }
    }

    /**
     * Método utilitário para criar uma resposta em formato de mapa.
     */
    private Map<String, String> createResponse(String key, String value) {
        Map<String, String> response = new HashMap<>();
        response.put(key, value);
        return response;
    }
}
