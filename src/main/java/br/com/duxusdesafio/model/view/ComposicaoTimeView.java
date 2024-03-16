package br.com.duxusdesafio.model.view;

import br.com.duxusdesafio.model.domain.Integrante;
import br.com.duxusdesafio.model.domain.Time;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComposicaoTimeView {
    private Long composicaoId;
    private Time time;
    private Integrante integrante;
}
