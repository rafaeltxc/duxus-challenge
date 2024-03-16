package br.com.duxusdesafio.model.view;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TimeView {
    private LocalDate data;
    private String descricao;
}
