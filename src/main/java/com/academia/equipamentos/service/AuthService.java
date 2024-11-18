package com.academia.equipamentos.service;

import com.academia.equipamentos.model.Usuario;
import com.academia.equipamentos.repository.UserRepository;
import com.academia.equipamentos.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager,
                       JwtProvider jwtProvider,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Autentica o usuário com base no nome de usuário e senha.
     *
     * @param username Nome de usuário.
     * @param password Senha.
     * @return Token JWT gerado após autenticação.
     * @throws BadCredentialsException Se as credenciais forem inválidas.
     */
    public String authenticate(String username, String password) throws BadCredentialsException {
        try {
            // Criando a token de autenticação
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Após autenticar o usuário, gerar o token JWT usando o username
            return jwtProvider.generateToken(username);  // Passando o nome de usuário diretamente
        } catch (BadCredentialsException e) {
            // Lançando a exceção se as credenciais forem inválidas
            throw new BadCredentialsException("Credenciais inválidas.");
        }
    }

    /**
     * Registra um novo usuário no sistema.
     *
     * @param usuario Objeto do tipo Usuario contendo os detalhes do usuário.
     * @return O usuário registrado.
     */
    public Usuario register(Usuario usuario) {
        if (userRepository.existsByUsername(usuario.getUsername())) {
            throw new IllegalArgumentException("O nome de usuário já está em uso.");
        }

        if (userRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("O e-mail já está em uso.");
        }

        // Codificando a senha antes de salvar no banco de dados
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return userRepository.save(usuario);
    }
}
