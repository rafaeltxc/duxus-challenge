package br.com.duxusdesafio.service;

import br.com.duxusdesafio.config.ModelMapperCf;
import br.com.duxusdesafio.model.domain.Time;
import br.com.duxusdesafio.model.input.TimeInput;
import br.com.duxusdesafio.model.view.TimeView;
import br.com.duxusdesafio.repositories.TimeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Time servico
 */
@Service
public class TimeService {
    @Autowired
    private TimeRepository tmRepository;
    @Autowired
    private ModelMapperCf mapper;

    /**
     * Busca todos os times da base de dados e os mapeia
     * para o objeto de visualizacao do usuario
     *
     * @return Lista de times
     */
    public List<TimeView> findAll() {
        List<Time> tms = tmRepository.findAll();
        return mapper.mapList(tms, TimeView.class);
    }

    /**
     * Busca um unico time da base de dados com base no identificado
     * e mapeia os para o objeto de vizualizacao do usuario
     *
     * @param id Identificador unico
     * @return Time
     */
    public TimeView findById(Long id) {
        Time it = tmRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.map(it, TimeView.class);
    }

    /**
     * Recebe um time, mapeia o objeto para o tipo de objeto padrao
     * e o salva na base da dados
     *
     * @param tmInput Input de um novo time
     * @return Time salvo
     */
    public TimeView saveOne(TimeInput tmInput) {
        Time tm = tmRepository.save(mapper.map(tmInput, Time.class));
        return mapper.map(tm, TimeView.class);
    }

    /**
     * Recebe um time, mapeia o objeto para o tipo de objeto padrao
     * e atualiza o objeto ja existente na base da dados com
     * base no identificador dado
     *
     * @param id Identificador unico
     * @param tmInput Input de um time com as devidas alteracoes
     * @return void
     */
    public void updateOne(Long id, TimeInput tmInput) {
        Time tm = tmRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        mapper.map(tmInput, tm);
        tmRepository.save(tm);
    }

    /**
     * Deleta um time da base de dados com base no itentificador dado
     *
     * @param id Identificador unico
     */
    public void deleteOne(Long id) {
        Time tm = tmRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        tmRepository.deleteById(id);
    }
}
