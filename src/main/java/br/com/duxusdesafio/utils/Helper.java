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
        // Classe do objeto
        Class<?> clazz = obj.getClass();

        // Loop pelas propriedades
        for (Field field: clazz.getDeclaredFields()) {
            // Configura acesso a propriedade
            field.setAccessible(true);

            // Validacao do tipo da propriedade
            if (field.getType().equals(LocalDate.class)) {
                // Inicia variavel
                T propertyValue = null;
                try {
                    // Defini varivael com o valor do campo obtido
                    propertyValue = (T) field.get(obj);
                } catch (IllegalAccessException e) {
                    // Lanca nova excessao em caso de erro
                    throw new RuntimeException(e);
                }
                // Retorna variable com a data obtida ou null
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
        // Cria uma stream com a lista de objetos
        return objs.stream()
                // Fitra os objetos com a logica de datas
                .filter(obj -> {
                    // Obtem data do objeto generico
                    LocalDate data = extrairData(obj);
                    // Checa se data do objeto e valido e adiciona a lista
                    return data.isAfter(dataInicial) && data.isBefore(dataFinal);
                })
                // Retorna lista com objetos obtidos
                .collect(Collectors.toList());
    }
}


