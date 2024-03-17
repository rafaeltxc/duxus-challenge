package br.com.duxusdesafio.service;

import br.com.duxusdesafio.config.ModelMapperCf;
import br.com.duxusdesafio.model.domain.ComposicaoTime;
import br.com.duxusdesafio.model.input.ComposicaoTimeInput;
import br.com.duxusdesafio.model.view.ComposicaoTimeView;
import br.com.duxusdesafio.repositories.ComposicaoTimeRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ComposicaoTimeService {
    @Autowired
    private ComposicaoTimeRepository cpRepository;
    @Autowired
    private ModelMapperCf mapper;

    public List<ComposicaoTimeView> findAll() {
        List<ComposicaoTime> cps = cpRepository.findAll();
        return mapper.mapList(cps, ComposicaoTimeView.class);
    }

    public ComposicaoTimeView findById(Long id) {
        ComposicaoTime cp = cpRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.map(cp, ComposicaoTimeView.class);
    }

    public ComposicaoTimeView saveOne(ComposicaoTimeInput cpInput) {
        ComposicaoTime cp = cpRepository.save(mapper.map(cpInput, ComposicaoTime.class));
        return mapper.map(cp, ComposicaoTimeView.class);
    }

    public void updateOne(Long id, ComposicaoTimeInput cpInput) {
        ComposicaoTime cp = cpRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        mapper.map(cpInput, cp);
        cpRepository.save(cp);
    }

    public void deleteOne(Long id) {
        ComposicaoTime cp = cpRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        cpRepository.deleteById(id);
    }
}
