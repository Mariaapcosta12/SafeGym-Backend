package com.academia.equipamentos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração da aplicação.
 * Contém definições de beans para serem gerenciados pelo contexto do Spring.
 */
@Configuration
public class AppConfig {

    /**
     * Exemplo de Bean gerenciado pelo Spring.
     * Este bean é apenas um exemplo e retorna uma string simples.
     *
     * @return uma string representando o bean de exemplo.
     */
    @Bean
    public String exampleBean() {
        return "This is an example bean";
    }
}
