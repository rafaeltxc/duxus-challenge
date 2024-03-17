package br.com.duxusdesafio.config;

import br.com.duxusdesafio.utils.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuracao de Beans
 */
@Configuration
public class BeanConfiguration {
    /**
     * Configura pacote ModelMapper para ser usado com SpringBoot
     *
     * @return new ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Configura ModelMapper personalizado
     *
     * @return new Custom ModelMapper
     */
    @Bean
    public ModelMapperCf modelMapperCf() {
        return new ModelMapperCf(new ModelMapper());
    }

    /**
     * Configura classe Helper Bean para ser usado com SpringBoot
     *
     * @return new Helper
     */
    @Bean
    public Helper helper() {
        return new Helper();
    }
}
