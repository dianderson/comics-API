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
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Integer finalIsbn;
    @NotNull
    private DayOfWeek dayOfWeek;
    @NotNull
    private Integer discountValue;

    public Discount(@NotNull Integer finalIsbn, @NotNull DayOfWeek dayOfWeek, @NotNull Integer discountValue) {
        this.finalIsbn = finalIsbn;
        this.dayOfWeek = dayOfWeek;
        this.discountValue = discountValue;
    }
}
