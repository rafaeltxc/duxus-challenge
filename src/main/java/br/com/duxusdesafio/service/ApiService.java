package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.domain.ComposicaoTime;
import br.com.duxusdesafio.model.domain.Integrante;
import br.com.duxusdesafio.model.domain.Time;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApiService {

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time daquela data
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        return todosOsTimes.stream()
                .filter(time -> time.getData().equals(data))
                .findFirst()
                .orElse(null);
    }

    /**
     * Vai retornar o integrante que tiver presente na maior quantidade de times
     * dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        List<Time> tms = todosOsTimes.stream()
                .filter(time -> !time.getData().isBefore(dataInicial) &&
                        !time.getData().isAfter(dataFinal))
                .collect(Collectors.toList());

        return tms.stream()
                .map(Time::getComposicaoTime)
                .flatMap(List::stream)
                .map(ComposicaoTime::getIntegrante)
                .min(Comparator.comparing(String::valueOf))
                .orElse(null);

    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */
    public List<String> timeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        List<Time> tms = todosOsTimes.stream()
                .filter(time -> !time.getData().isBefore(dataInicial) &&
                        !time.getData().isAfter(dataFinal))
                .collect(Collectors.toList());

        LocalDate tmData = tms.stream()
                .map(Time::getData)
                .max(LocalDate::compareTo)
                .get();

        Time tm = todosOsTimes.stream()
                .filter(time -> time.getData().equals(tmData))
                .findAny()
                .get();

        return tm.getComposicaoTime().stream()
                .map(ComposicaoTime::getIntegrante)
                .map(Integrante::getNome)
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        List<Time> tms = todosOsTimes.stream()
                .filter(time -> !time.getData().isBefore(dataInicial) &&
                        !time.getData().isAfter(dataFinal))
                .collect(Collectors.toList());

        List<Integrante> its = tms.stream()
                .map(Time::getComposicaoTime)
                .flatMap(List::stream)
                .map(ComposicaoTime::getIntegrante)
                .collect(Collectors.toList());

        return its.stream()
                .map(Integrante::getFuncao)
                .min(Comparator.comparing(String::valueOf))
                .map(Objects::toString)
                .orElse(null);
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        List<Time> tms = todosOsTimes.stream()
                .filter(time -> !time.getData().isBefore(dataInicial) &&
                        !time.getData().isAfter(dataFinal))
                .collect(Collectors.toList());

        List<Integrante> its = tms.stream()
                .map(Time::getComposicaoTime)
                .flatMap(List::stream)
                .map(ComposicaoTime::getIntegrante)
                .collect(Collectors.toList());

        return its.stream()
                .map(Integrante::getFranquia)
                .min(Comparator.comparing(String::valueOf))
                .map(Objects::toString)
                .orElse(null);
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        List<Time> tms = todosOsTimes.stream()
                .filter(time -> time.getData().isAfter(dataInicial) &&
                        time.getData().isBefore(dataFinal))
                .collect(Collectors.toList());

        HashMap<String, Long> map = new HashMap<>();
        tms.stream()
                .map(Time::getComposicaoTime)
                .flatMap(List::stream)
                .map(ComposicaoTime::getIntegrante)
                .forEach(it -> {
                    map.put(it.getFranquia(),
                            map.getOrDefault(it.getFranquia(), 0L) + 1);
                });

        return map;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        List<Time> tms = todosOsTimes.stream()
                .filter(time -> time.getData().isAfter(dataInicial) &&
                        time.getData().isBefore(dataFinal))
                .collect(Collectors.toList());

        HashMap<String, Long> map = new HashMap<>();
        tms.stream()
                .map(Time::getComposicaoTime)
                .flatMap(List::stream)
                .map(ComposicaoTime::getIntegrante)
                .forEach(it -> {
                    map.put(it.getFuncao(),
                            map.getOrDefault(it.getFuncao(), 0L) + 1);
                });

        return map;
    }

}
