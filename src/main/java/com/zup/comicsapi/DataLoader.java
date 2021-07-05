package com.zup.comicsapi;

import com.zup.comicsapi.repository.model.Desconto;
import com.zup.comicsapi.service.DescontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
@RequiredArgsConstructor
public class DataLoader {
    private final DescontoService descontoService;

    public void loadData() {
        descontoService.save(new Desconto(0, DayOfWeek.MONDAY, 10));
        descontoService.save(new Desconto(1, DayOfWeek.MONDAY, 10));
        descontoService.save(new Desconto(2, DayOfWeek.TUESDAY, 10));
        descontoService.save(new Desconto(3, DayOfWeek.TUESDAY, 10));
        descontoService.save(new Desconto(4, DayOfWeek.WEDNESDAY, 10));
        descontoService.save(new Desconto(5, DayOfWeek.WEDNESDAY, 10));
        descontoService.save(new Desconto(6, DayOfWeek.THURSDAY, 10));
        descontoService.save(new Desconto(7, DayOfWeek.THURSDAY, 10));
        descontoService.save(new Desconto(8, DayOfWeek.FRIDAY, 10));
        descontoService.save(new Desconto(9, DayOfWeek.FRIDAY, 10));
    }
}
