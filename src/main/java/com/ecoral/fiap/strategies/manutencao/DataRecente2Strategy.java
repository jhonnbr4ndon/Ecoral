package com.ecoral.fiap.strategies.manutencao;

import com.ecoral.fiap.entities.Manutencao;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataRecente2Strategy implements ManutencaoStrategy {

    @Override
    public List<Manutencao> organizar(List<Manutencao> manutencaos) {
        return manutencaos.stream()
                .sorted(Comparator.comparing(Manutencao::getData).reversed())
                .collect(Collectors.toList());
    }
}
