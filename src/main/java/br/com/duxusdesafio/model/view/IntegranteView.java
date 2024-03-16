package br.com.duxusdesafio.model.view;

import br.com.duxusdesafio.model.domain.ComposicaoTime;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IntegranteView {
    private Long integranteId;
    private String nome;
    private String funcao;
    private String franquia;
    private List<ComposicaoTime> composicaoTime;
}
