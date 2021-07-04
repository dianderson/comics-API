package com.zup.comicsAPI.repository.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Descontos {
    @Id
    @NotNull
    private int finalIsbn;
    @NotNull
    private DiasDaSemana diaDaSemana;
    @NotNull
    private int valorDesconto;
}
