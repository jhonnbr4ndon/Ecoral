package com.ecoral.fiap.strategies.manutencao;

import com.ecoral.fiap.entities.Manutencao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Primary
@Component
public class NomeResponsavel2Strategy implements ManutencaoStrategy {

    @Override
    public List<Manutencao> organizar(List<Manutencao> manutencaos) {
        return manutencaos.stream()
                .sorted(Comparator.comparing(Manutencao::getResponsavel))
                .collect(Collectors.toList());
    }
}
