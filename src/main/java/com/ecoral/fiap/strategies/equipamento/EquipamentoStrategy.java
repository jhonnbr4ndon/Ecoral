package com.ecoral.fiap.strategies.equipamento;

import com.ecoral.fiap.entities.Equipamento;

import java.util.List;

public interface EquipamentoStrategy {

    List<Equipamento> organizar(List<Equipamento> equipamentos);
}
