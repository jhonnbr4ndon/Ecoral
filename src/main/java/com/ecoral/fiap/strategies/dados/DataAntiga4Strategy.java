package com.ecoral.fiap.strategies.dados;

import com.ecoral.fiap.entities.Dados;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataAntiga4Strategy implements DadosStrategy {

    @Override
    public List<Dados> organizar(List<Dados> dados) {
        return dados.stream()
                .sorted(Comparator.comparing(Dados::getData))
                .collect(Collectors.toList());
    }
}
