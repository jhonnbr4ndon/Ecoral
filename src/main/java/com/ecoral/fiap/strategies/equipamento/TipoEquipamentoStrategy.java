package com.ecoral.fiap.strategies.equipamento;

import com.ecoral.fiap.entities.Equipamento;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Primary
@Component
public class TipoEquipamentoStrategy implements EquipamentoStrategy {

    @Override
    public List<Equipamento> organizar(List<Equipamento> equipamentos) {
        return equipamentos.stream()
                .sorted(Comparator.comparing(Equipamento::getTipo))
                .collect(Collectors.toList());
    }
}
