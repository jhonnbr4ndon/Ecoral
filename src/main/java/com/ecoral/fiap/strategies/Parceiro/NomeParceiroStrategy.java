package com.ecoral.fiap.strategies.parceiro;

import com.ecoral.fiap.entities.Parceiro;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Primary
@Component
public class NomeParceiroStrategy implements ParceiroStrategy {

    @Override
    public List<Parceiro> organizar(List<Parceiro> parceiros) {
        return parceiros.stream()
                .sorted(Comparator.comparing(Parceiro::getNome))
                .collect(Collectors.toList());
    }
}
