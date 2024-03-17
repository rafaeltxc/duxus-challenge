package br.com.duxusdesafio.config;

import br.com.duxusdesafio.utils.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ModelMapperCf modelMapperCf() {
        return new ModelMapperCf(new ModelMapper());
    }

    @Bean
    public Helper helper() {
        return new Helper();
    }
}
