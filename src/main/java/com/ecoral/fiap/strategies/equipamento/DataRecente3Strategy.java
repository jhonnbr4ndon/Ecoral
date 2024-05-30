package com.ecoral.fiap.strategies.equipamento;

import com.ecoral.fiap.entities.Equipamento;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataRecente3Strategy implements EquipamentoStrategy {

    @Override
    public List<Equipamento> organizar(List<Equipamento> equipamentos) {
        return equipamentos.stream()
                .sorted(Comparator.comparing(Equipamento::getData).reversed())
                .collect(Collectors.toList());
    }
}
