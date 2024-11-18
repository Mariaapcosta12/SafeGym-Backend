package com.academia.equipamentos.config;

import com.academia.equipamentos.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe de configuração de segurança para o sistema.
 * Configura as políticas de autenticação, autorização e gerenciamento de sessão.
 */
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Injeta o filtro de autenticação JWT.
     *
     * @param jwtAuthenticationFilter Filtro responsável por validar tokens JWT.
     */
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * Configura o filtro de segurança da aplicação.
     *
     * @param http Objeto HttpSecurity usado para configurar as regras de segurança.
     * @return Configuração da cadeia de filtros de segurança.
     * @throws Exception Em caso de erro na configuração.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Desativa CSRF (não necessário para APIs REST)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura a sessão para ser sem estado (stateless)
                .authorizeRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll() // Permitir acesso sem autenticação a essas rotas
                        .anyRequest().authenticated() // Todas as outras rotas requerem autenticação
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona o filtro JWT antes do filtro de autenticação padrão
                .build();
    }

    /**
     * Configura o gerenciador de autenticação.
     *
     * @param configuration Configuração de autenticação do Spring.
     * @return Um gerenciador de autenticação configurado.
     * @throws Exception Em caso de erro na configuração.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Configura o codificador de senhas usando BCrypt.
     *
     * @return Um codificador de senhas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Retorna uma instância do BCryptPasswordEncoder
    }
}
