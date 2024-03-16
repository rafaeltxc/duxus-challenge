package br.com.duxusdesafio.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ModelMapperCf {

    private final ModelMapper modelMapper;

    public void map(Object source, Object destination) {
        modelMapper.map(source, destination);
    }

    public <D> D map(Object source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public <SOURCE, TARGET> List<TARGET> mapList(List<SOURCE> source, Class<TARGET> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
