package br.com.duxusdesafio.model.view;

import br.com.duxusdesafio.model.domain.ComposicaoTime;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TimeView {
    private LocalDate data;
    private String descricao;
    private List<ComposicaoTime> composicaoTime;
}
