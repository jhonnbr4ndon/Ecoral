package com.ecoral.fiap.strategies.dados;

import com.ecoral.fiap.entities.Dados;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Primary
@Component
public class DataRecente4Strategy implements DadosStrategy {

    @Override
    public List<Dados> organizar(List<Dados> dados) {
        return dados.stream()
                .sorted(Comparator.comparing(Dados::getData).reversed())
                .collect(Collectors.toList());
    }
}
