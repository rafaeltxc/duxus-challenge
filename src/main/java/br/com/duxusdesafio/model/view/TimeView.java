package br.com.duxusdesafio.model.view;

import br.com.duxusdesafio.model.domain.ComposicaoTime;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TimeView {
    private Long timeId;
    private String nome;
    private LocalDate data;
    private List<ComposicaoTime> composicaoTime;
}
