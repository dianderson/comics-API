package com.zup.comicsapi;

import com.zup.comicsapi.repository.model.Discount;
import com.zup.comicsapi.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
@RequiredArgsConstructor
public class DataLoader {
    private final DiscountService discountService;

    public void loadData() {
        discountService.save(new Discount(0, DayOfWeek.MONDAY, 10));
        discountService.save(new Discount(1, DayOfWeek.MONDAY, 10));
        discountService.save(new Discount(2, DayOfWeek.TUESDAY, 10));
        discountService.save(new Discount(3, DayOfWeek.TUESDAY, 10));
        discountService.save(new Discount(4, DayOfWeek.WEDNESDAY, 10));
        discountService.save(new Discount(5, DayOfWeek.WEDNESDAY, 10));
        discountService.save(new Discount(6, DayOfWeek.THURSDAY, 10));
        discountService.save(new Discount(7, DayOfWeek.THURSDAY, 10));
        discountService.save(new Discount(8, DayOfWeek.FRIDAY, 10));
        discountService.save(new Discount(9, DayOfWeek.FRIDAY, 10));
    }
}
