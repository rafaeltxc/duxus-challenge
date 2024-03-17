package br.com.duxusdesafio.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe de personalizacao do pacote ModelMapper
 */
@RequiredArgsConstructor
public class ModelMapperCf {

    private final ModelMapper modelMapper;

    /**
     * Funcao generica para a troca de propriedades de um objeto
     * a partir das propriedades de um outro objeto
     *
     * @param source Objeto com alteracoes
     * @param destination Objeto para ser alterado
     */
    public void map(Object source, Object destination) {
        modelMapper.map(source, destination);
    }

    /**
     * Funcao generica para interpolar um objeto de uma classe em um
     * objeto de outra classe
     *
     * @param source Objeto que sera interpolado
     * @param destinationType Classe para o qual o objeto vai ser interpolado
     * @return Objeto interpolado
     */
    public <D> D map(Object source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    /**
     * Funcao generica para interpolar uma lista de objetos de uma classe
     * em um objeto de outra classe
     *
     * @param source Objetos a serem interpolados
     * @param targetClass Classe para qual os objetos vao ser interpolados
     * @return List de objetos ja alterados
     */
    public <SOURCE, TARGET> List<TARGET> mapList(List<SOURCE> source, Class<TARGET> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
