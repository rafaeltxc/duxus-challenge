package br.com.duxusdesafio.service;

import br.com.duxusdesafio.config.ModelMapperCf;
import br.com.duxusdesafio.model.domain.ComposicaoTime;
import br.com.duxusdesafio.model.domain.Integrante;
import br.com.duxusdesafio.model.domain.Time;
import br.com.duxusdesafio.model.input.IntegranteInput;
import br.com.duxusdesafio.model.view.IntegranteView;
import br.com.duxusdesafio.repositories.IntegranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IntegranteService {
    @Autowired
    private IntegranteRepository itRepository;
    @Autowired
    private ModelMapperCf mapper;

    public List<IntegranteView> findAll() {
        List<Integrante> its = itRepository.findAll();
        return mapper.mapList(its, IntegranteView.class);
    }

    public IntegranteView findById(Long id) {
        Integrante it = itRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.map(it, IntegranteView.class);
    }

    public IntegranteView saveOne(IntegranteInput itInput) {
        Integrante it = itRepository.save(mapper.map(itInput, Integrante.class));
        return mapper.map(it, IntegranteView.class);
    }

    public void updateOne(Long id, IntegranteInput itInput) {
        Integrante it = itRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        mapper.map(itInput, it);
        itRepository.save(it);
    }

    public void deleteOne(Long id) {
        Integrante it = itRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        itRepository.deleteById(id);
    }

    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        return todosOsTimes.stream()
                .filter(time -> time.getData().equals(data))
                .findFirst()
                .orElse(null);
    }

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
