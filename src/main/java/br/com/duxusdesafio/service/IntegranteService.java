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
     * Retorna um time no qual a data e igual a data dada
     *
     * @param data Data
     * @param todosOsTimes Lista de todos os times
     * @return Time
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        // Cria um stream a partir da lista de times
        return todosOsTimes.stream()
                // Filtra o time com a data correspondente a data dada
                .filter(time -> time.getData().equals(data))
                // Pega o primeiro resultado encontrado
                .findFirst()
                // Caso nao haja resultado, retorna null
                .orElse(null);
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
        // Busca os times com data correspondente ao respectivo periodo dado
        List<Time> tms = helper.achaPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        // Cria um stream a partir da lista de times encontrados
        return tms.stream()
                // Retorna uma lista de ComposicaoTime
                .map(Time::getComposicaoTime)
                // Tranforma lista em uma stream
                .flatMap(List::stream)
                // Retorna lista com todos os integrantes de todas as composicoes
                .map(ComposicaoTime::getIntegrante)
                // Busca o integrante mais comum
                .min(Comparator.comparing(String::valueOf))
                // Caso nao haja resultado, retorna null
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
        // Busca os times com data correspondente ao respectivo periodo dado
        List<Time> tms = helper.achaPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        // Cria um stream a partir da lista de times encontrados
        LocalDate tmData = tms.stream()
                // Retorna lista de datas de todos os times
                .map(Time::getData)
                // Retorna data mais comum
                .max(LocalDate::compareTo)
                // Retorna resultado
                .get();

        // Cria um stream a partir da lista de times econtrados
        Time tm = tms.stream()
                // Busca o time no qual a data e igual a data econtrada ne variavel anterior
                .filter(time -> time.getData().equals(tmData))
                // Retorna qualquer item da lista no qual a data seja valida
                .findAny()
                // Retorna resultado
                .get();

        // Cria um stream a partir da lista de composicoes do time encontrado
        return tm.getComposicaoTime().stream()
                // Retorna lista de integrantes
                .map(ComposicaoTime::getIntegrante)
                // Retorna nome dos integrantes
                .map(Integrante::getNome)
                // Retorna lista de resultados
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
        // Busca os times com data correspondente ao respectivo periodo dado
        List<Time> tms = helper.achaPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        // Cria um stream a partir da lista de times econtrados
        List<Integrante> its = tms.stream()
                // Retorna lista de composicoes
                .map(Time::getComposicaoTime)
                // Transforma lista em stream
                .flatMap(List::stream)
                // Retorna lista de integrantes
                .map(ComposicaoTime::getIntegrante)
                // Retorna lista com os resultados
                .collect(Collectors.toList());

        // Cria um stream a partir da lista de integrantes econtrados
        return its.stream()
                // Retorna lista de funcoes
                .map(Integrante::getFuncao)
                // Retorna a funcao mais comum
                .min(Comparator.comparing(String::valueOf))
                // Caso nao haja resultado, retorna null
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
        // Busca os times com data correspondente ao respectivo periodo dado
        List<Time> tms = helper.achaPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        // Cria um stream a partir da lista de times econtrados
        List<Integrante> its = tms.stream()
                // Retorna lista de composicoes
                .map(Time::getComposicaoTime)
                // Transforma lista em stream
                .flatMap(List::stream)
                // Retorna lista de integrantes
                .map(ComposicaoTime::getIntegrante)
                // Retorna lista com os resultados
                .collect(Collectors.toList());

        // Cria um stream a partir da lista de integrantes econtrados
        return its.stream()
                // Retorna lista de franquias
                .map(Integrante::getFranquia)
                // Retorna a franquia mais comum
                .min(Comparator.comparing(String::valueOf))
                // Caso nao haja resultado, retorna null
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
        // Busca os times com data correspondente ao respectivo periodo dado
        List<Time> tms = helper.achaPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        // Cria um novo HashMap
        HashMap<String, Long> map = new HashMap<>();
        // Cria um stream a partir da lista de times econtrados
        tms.stream()
                // Retorna lista de composicoes
                .map(Time::getComposicaoTime)
                // Transforma lista em stream
                .flatMap(List::stream)
                // Retorna lista de integrantes
                .map(ComposicaoTime::getIntegrante)
                // Loop em cada integrante encontrado
                .forEach(it -> {
                    // Inclui cada franquia no HashMap e atualiza a sua quantidade
                    map.put(it.getFranquia(),
                            map.getOrDefault(it.getFranquia(), 0L) + 1);
                });

        // Retorna HashMap com resultado
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
        // Busca os times com data correspondente ao respectivo periodo dado
        List<Time> tms = helper.achaPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        // Cria um novo HashMap
        HashMap<String, Long> map = new HashMap<>();
        // Cria um stream a partir da lista de times econtrados
        tms.stream()
                // Retorna lista de composicoes
                .map(Time::getComposicaoTime)
                // Transforma lista em stream
                .flatMap(List::stream)
                // Retorna lista de integrantes
                .map(ComposicaoTime::getIntegrante)
                // Loop em cada integrante encontrado
                .forEach(it -> {
                    // Inclui cada funcao no HashMap e atualiza a sua quantidade
                    map.put(it.getFuncao(),
                            map.getOrDefault(it.getFuncao(), 0L) + 1);
                });

        // Retorna HashMap com resultado
        return map;
    }
}
