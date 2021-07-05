package com.zup.comicsapi.repository.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;

@Data
@Entity
@RequiredArgsConstructor
public class Desconto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Integer finalIsbn;
    @NotNull
    private DayOfWeek diaDaSemana;
    @NotNull
    private Integer valorDesconto;

    public Desconto(@NotNull Integer finalIsbn, @NotNull DayOfWeek diaDaSemana, @NotNull Integer valorDesconto) {
        this.finalIsbn = finalIsbn;
        this.diaDaSemana = diaDaSemana;
        this.valorDesconto = valorDesconto;
    }
}
