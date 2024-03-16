package br.com.duxusdesafio.service;

import br.com.duxusdesafio.config.ModelMapperCf;
import br.com.duxusdesafio.model.domain.Integrante;
import br.com.duxusdesafio.model.input.IntegranteInput;
import br.com.duxusdesafio.model.view.IntegranteView;
import br.com.duxusdesafio.repositories.IntegranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IntegranteService {
    private final IntegranteRepository itRepository;
    private final ModelMapperCf mapper;

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
}
