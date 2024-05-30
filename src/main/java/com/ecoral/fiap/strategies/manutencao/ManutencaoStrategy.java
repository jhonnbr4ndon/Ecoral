package com.ecoral.fiap.strategies.manutencao;

import com.ecoral.fiap.entities.Manutencao;

import java.util.List;

public interface ManutencaoStrategy {

    List<Manutencao> organizar(List<Manutencao> manutencaos);
}
