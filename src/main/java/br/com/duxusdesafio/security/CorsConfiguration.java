package br.com.duxusdesafio.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Classe para configuracao de cors
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    /**
     * Mapeia permissoes cors
     *
     * @param registry Registro de mapeamento
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
