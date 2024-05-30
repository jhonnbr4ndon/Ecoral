package com.ecoral.fiap.strategies.alerta;

import com.ecoral.fiap.entities.Alerta;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataAntiga5Strategy implements AlertaStrategy {

    @Override
    public List<Alerta> organizar(List<Alerta> alertas) {
        return alertas.stream()
                .sorted(Comparator.comparing(Alerta::getData))
                .collect(Collectors.toList());
    }
}
