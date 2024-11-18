package com.academia.equipamentos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração de CORS para permitir requisições entre domínios.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configura as permissões de CORS para o backend.
     *
     * Permite requisições do frontend hospedado em http://localhost:3000
     * e habilita os métodos GET, POST, PUT, DELETE e OPTIONS.
     *
     * @param registry O objeto para configurar as regras de CORS.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a configuração a todas as rotas
                .allowedOrigins("http://localhost:3000") // Permite requisições do frontend no localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Habilita os métodos HTTP suportados
                .allowedHeaders("*") // Permite todos os cabeçalhos HTTP
                .exposedHeaders("Authorization") // Expõe cabeçalhos específicos para o frontend
                .allowCredentials(true); // Habilita envio de cookies ou credenciais
    }
}

