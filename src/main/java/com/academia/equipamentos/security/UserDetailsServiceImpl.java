package com.academia.equipamentos.security;

import com.academia.equipamentos.model.Usuario;
import com.academia.equipamentos.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no repositório pelo nome de usuário
        Usuario usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        // Retorna os detalhes do usuário com o nome de usuário, senha criptografada e a role
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getSenha())  // A senha já deve estar criptografada
                .roles(usuario.getStatus().name()) // A role é o status do usuário (ATIVO, INATIVO)
                .build();
    }
}
