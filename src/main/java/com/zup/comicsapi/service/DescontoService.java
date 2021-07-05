package com.zup.comicsapi.service;

import com.zup.comicsapi.repository.DescontoRepository;
import com.zup.comicsapi.repository.model.Desconto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DescontoService {
    private final DescontoRepository descontoRepository;

    public Desconto save(Desconto desconto) {
        return descontoRepository.save(desconto);
    }

    public Desconto findByFinalIsbn(Integer finalIsbn) {
        return descontoRepository.findByfinalIsbn(finalIsbn);
    }
}
