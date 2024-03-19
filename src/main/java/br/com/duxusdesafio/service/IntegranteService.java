package br.com.duxusdesafio.service;

import br.com.duxusdesafio.config.ModelMapperCf;
import br.com.duxusdesafio.model.domain.ComposicaoTime;
import br.com.duxusdesafio.model.domain.Integrante;
import br.com.duxusdesafio.model.domain.Time;
import br.com.duxusdesafio.model.input.IntegranteInput;
import br.com.duxusdesafio.model.view.IntegranteView;
import br.com.duxusdesafio.repositories.IntegranteRepository;
import br.com.duxusdesafio.utils.Helper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Integrante servico
 */
@Service
public class IntegranteService {
    @Autowired
    private IntegranteRepository itRepository;
    @Autowired
    private ModelMapperCf mapper;
    @Autowired
    private Helper helper;

    public IntegranteService(Helper helper) {
        this.helper = helper;
    }

    /**
     * Busca todos os integrantes da base de dados e os mapeia
     * para o objeto de visualizacao do usuario
     *
     * @return Lista de integrantes
     */
    public List<IntegranteView> findAll() {
        List<Integrante> its = itRepository.findAll();
        return mapper.mapList(its, IntegranteView.class);
    }

    /**
     * Busca um unico integrante da base de dados com base no identificado
     * e mapeia os para o objeto de vizualizacao do usuario
     *
     * @param id Identificador unico
     * @return Integrante
     */
    public IntegranteView findById(Long id) {
        Integrante it = itRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.map(it, IntegranteView.class);
    }

    /**
     * Recebe um integrante, mapeia o objeto para o tipo de objeto padrao
     * e o salva na base da dados
     *
     * @param itInput Input de um novo integrante
     * @return Integrante salvo
     */
    public IntegranteView saveOne(IntegranteInput itInput) {
        Integrante it = itRepository.save(mapper.map(itInput, Integrante.class));
        return mapper.map(it, IntegranteView.class);
    }

    /**
     * Recebe um integrante, mapeia o objeto para o tipo de objeto padrao
     * e atualiza o objeto ja existente na base da dados com
     * base no identificador dado
     *
     * @param id Identificador unico
     * @param itInput Input de um integrante com as devidas alteracoes
     * @return void
     */
    public void updateOne(Long id, IntegranteInput itInput) {
        Integrante it = itRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        mapper.map(itInput, it);
        itRepository.save(it);
    }

    /**
     * Deleta um integrante da base de dados com base no itentificador dado
     *
     * @param id Identificador unico
     */
    public void deleteOne(Long id) {
        Integrante it = itRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        itRepository.deleteById(id);
    }

    /**
     * Retorna integrante mais usado nas composicoes de todos os times
     *
     * @param dataInicial Data de inicio
     * @param dataFinal Data final
     * @param todosOsTimes Lista de todos os times
     * @return Integrate
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        List<Time> tms = helper.achaPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        return tms.stream()
                .map(Time::getComposicaoTime)
                .flatMap(List::stream)
                .map(ComposicaoTime::getIntegrante)
                .min(Comparator.comparing(String::valueOf))
                .orElse(null);
    }

    /**
     * Retorna list com nome de cada integrante a partir do time econtrado
     * dentro do periodo dado
     *
     * @param dataInicial Data de inicio
     * @param dataFinal Data final
     * @param todosOsTimes Lista de todos os times
     * @return Lista com nome dos integrantes
     */
    public List<String> timeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        List<Time> tms = helper.achaPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        LocalDate tmData = tms.stream()
                .map(Time::getData)
                .max(LocalDate::compareTo)
                .get();

        Time tm = tms.stream()
                .filter(time -> time.getData().equals(tmData))
                .findAny()
                .get();

        return tm.getComposicaoTime().stream()
                .map(ComposicaoTime::getIntegrante)
                .map(Integrante::getNome)
                .collect(Collectors.toList());
    }

    /**
     * Retorna funcao mais comum com base nos integrantes de todas as composicoes
     *
     * @param dataInicial Data de inicio
     * @param dataFinal Data final
     * @param todosOsTimes Lista de todos os times
     * @return Funcao mais comum
     */
    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        List<Time> tms = helper.achaPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        List<Integrante> its = tms.stream()
                .map(Time::getComposicaoTime)
                .flatMap(List::stream)
                .map(ComposicaoTime::getIntegrante)
                .collect(Collectors.toList());

        return its.stream()
                .map(Integrante::getFuncao)
                .min(Comparator.comparing(String::valueOf))
                .orElse(null);
    }

    /**
     * Retorna franquia mais comum com base nos integrantes de todas as composicoes
     *
     * @param dataInicial Data de inicio
     * @param dataFinal Data final
     * @param todosOsTimes Lista de todos os times
     * @return Franquia mais comum
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        List<Time> tms = helper.achaPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        List<Integrante> its = tms.stream()
                .map(Time::getComposicaoTime)
                .flatMap(List::stream)
                .map(ComposicaoTime::getIntegrante)
                .collect(Collectors.toList());

        return its.stream()
                .map(Integrante::getFranquia)
                .min(Comparator.comparing(String::valueOf))
                .orElse(null);
    }

    /**
     * Retorna franquia mais comum e sua quantidade
     *
     * @param dataInicial Data de inicio
     * @param dataFinal Data final
     * @param todosOsTimes Lista de todos os times
     * @return Franquia mais comum
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        List<Time> tms = helper.achaPorPeriodo(dataInicial, dataFinal, todosOsTimes);

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
     * Retorna funcao mais comum e sua quantidade
     *
     * @param dataInicial Data de inicio
     * @param dataFinal Data final
     * @param todosOsTimes Lista de todos os times
     * @return Franquia mais comum
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        List<Time> tms = helper.achaPorPeriodo(dataInicial, dataFinal, todosOsTimes);

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
