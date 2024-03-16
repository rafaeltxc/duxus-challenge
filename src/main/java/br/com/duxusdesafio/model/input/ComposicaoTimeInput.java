package br.com.duxusdesafio.model.input;

import br.com.duxusdesafio.model.domain.Integrante;
import br.com.duxusdesafio.model.domain.Time;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComposicaoTimeInput {
    private Time time;
    private Integrante integrante;
}
