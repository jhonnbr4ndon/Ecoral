package com.ecoral.fiap.strategies.relatorio;

import com.ecoral.fiap.entities.Relatorio;

import java.util.List;

public interface RelatorioStrategy {

    List<Relatorio> organizar(List<Relatorio> relatorios);
}
