package br.com.duxusdesafio.service;

import br.com.duxusdesafio.config.ModelMapperCf;
import br.com.duxusdesafio.model.domain.Time;
import br.com.duxusdesafio.model.input.TimeInput;
import br.com.duxusdesafio.model.view.TimeView;
import br.com.duxusdesafio.repositories.TimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeService {
    private final TimeRepository tmRepository;
    private final ModelMapperCf mapper;

    public List<TimeView> findAll() {
        List<Time> tms = tmRepository.findAll();
        return mapper.mapList(tms, TimeView.class);
    }

    public TimeView findById(Long id) {
        Time it = tmRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.map(it, TimeView.class);
    }

    public TimeView saveOne(TimeInput tmInput) {
        Time tm = tmRepository.save(mapper.map(tmInput, Time.class));
        return mapper.map(tm, TimeView.class);
    }

    public void updateOne(Long id, TimeInput tmInput) {
        Time tm = tmRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        mapper.map(tmInput, tm);
        tmRepository.save(tm);
    }

    public void deleteOne(Long id) {
        Time tm = tmRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        tmRepository.deleteById(id);
    }
}
