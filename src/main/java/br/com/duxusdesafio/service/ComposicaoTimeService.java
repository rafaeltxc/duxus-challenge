package br.com.duxusdesafio.service;

import br.com.duxusdesafio.config.ModelMapperCf;
import br.com.duxusdesafio.model.domain.ComposicaoTime;
import br.com.duxusdesafio.model.input.ComposicaoTimeInput;
import br.com.duxusdesafio.model.view.ComposicaoTimeView;
import br.com.duxusdesafio.repositories.ComposicaoTimeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ComposicaoTime servico
 */
@Service
public class ComposicaoTimeService {
    @Autowired
    private ComposicaoTimeRepository cpRepository;
    @Autowired
    private ModelMapperCf mapper;

    /**
     * Busca todas as composicoes da base de dados e os mapeia
     * para o objeto de visualizacao do usuario
     *
     * @return Lista de composicoes
     */
    public List<ComposicaoTimeView> findAll() {
        List<ComposicaoTime> cps = cpRepository.findAll();
        return mapper.mapList(cps, ComposicaoTimeView.class);
    }

    /**
     * Busca uma unica composicao da base de dados com base no identificado
     * e mapeia os para o objeto de vizualizacao do usuario
     *
     * @param id Identificador unico
     * @return Composicao
     */
    public ComposicaoTimeView findById(Long id) {
        ComposicaoTime cp = cpRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.map(cp, ComposicaoTimeView.class);
    }

    /**
     * Recebe uma composicao, mapeia o objeto para o tipo de objeto padrao
     * e o salva na base da dados
     *
     * @param cpInput Input de uma nova composicao
     * @return Composicao salvo
     */
    public ComposicaoTimeView saveOne(ComposicaoTimeInput cpInput) {
        ComposicaoTime cp = cpRepository.save(mapper.map(cpInput, ComposicaoTime.class));
        return mapper.map(cp, ComposicaoTimeView.class);
    }

    /**
     * Recebe uma composicao, mapeia o objeto para o tipo de objeto padrao
     * e atualiza o objeto ja existente na base da dados com
     * base no identificador dado
     *
     * @param id Identificador unico
     * @param cpInput Input de uma composicao com as devidas alteracoes
     * @return void
     */
    public void updateOne(Long id, ComposicaoTimeInput cpInput) {
        ComposicaoTime cp = cpRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        mapper.map(cpInput, cp);
        cpRepository.save(cp);
    }

    /**
     * Deleta uma composicao da base de dados com base no itentificador dado
     *
     * @param id Identificador unico
     */
    public void deleteOne(Long id) {
        ComposicaoTime cp = cpRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        cpRepository.deleteById(id);
    }
}
