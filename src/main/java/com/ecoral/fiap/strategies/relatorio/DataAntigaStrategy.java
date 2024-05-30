package com.ecoral.fiap.strategies.relatorio;

import com.ecoral.fiap.entities.Relatorio;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataAntigaStrategy implements RelatorioStrategy {

    @Override
    public List<Relatorio> organizar(List<Relatorio> relatorios) {
        return relatorios.stream()
                .sorted(Comparator.comparing(Relatorio::getData))
                .collect(Collectors.toList());
    }
}
