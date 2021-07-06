package com.zup.comicsapi.service;

import com.zup.comicsapi.repository.DiscountRepository;
import com.zup.comicsapi.repository.model.Discount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountRepository discountRepository;

    public Discount save(Discount discount) {
        return discountRepository.save(discount);
    }

    public Discount findByFinalIsbn(Integer finalIsbn) {
        return discountRepository.findByFinalIsbn(finalIsbn);
    }
}
