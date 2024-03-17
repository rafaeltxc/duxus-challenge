package br.com.duxusdesafio.utils;

import br.com.duxusdesafio.model.domain.Time;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Helper {
    public <T> T extrairData(Object obj) {
        Class<?> clazz = obj.getClass();

        for (Field field: clazz.getDeclaredFields()) {
            field.setAccessible(true);

            if (field.getType().equals(LocalDate.class)) {
                T propertyValue = null;
                try {
                    propertyValue = (T) field.get(obj);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                return propertyValue;
            }
        }
        return null;
    }

    public <D> List<D> achaPorPeriodo(LocalDate dataInicial, LocalDate dataFinal, List<D> objs) {
        return objs.stream()
                .filter(obj -> {
                    LocalDate data = extrairData(obj);
                    return data.isAfter(dataInicial) && data.isBefore(dataFinal);
                })
                .collect(Collectors.toList());
    }
}


