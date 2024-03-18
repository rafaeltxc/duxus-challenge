package br.com.duxusdesafio.model.input;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TimeInput {
    private String nome;
    private LocalDate data;
}
