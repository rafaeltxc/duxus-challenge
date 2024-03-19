package br.com.duxusdesafio.utils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe para logica de funcoes com frequente uso
 */
public class Helper {
    /**
     * Extrai de forma generica a primeira propriedade de um objeto no qual
     * o tipo seja igual a LocalDate com o uso de reflection
     *
     * @param obj Objeto do qual a data sera extraida
     * @return Data extraida
     */
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

    /**
     * Funcao generica para achar todos os itens de uma lista dentro de
     * um periodo de tempo
     *
     * @param dataInicial Data inicial do periodo
     * @param dataFinal Data final do peiodo
     * @param objs Lista de objetos para serem filtrados
     * @return List de objetos obtidos
     */
    public <D> List<D> achaPorPeriodo(LocalDate dataInicial, LocalDate dataFinal, List<D> objs) {
        return objs.stream()
                .filter(obj -> {
                    LocalDate data = extrairData(obj);
                    return data.isAfter(dataInicial) && data.isBefore(dataFinal);
                })
                .collect(Collectors.toList());
    }
}


