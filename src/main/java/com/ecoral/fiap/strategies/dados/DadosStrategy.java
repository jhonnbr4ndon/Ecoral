package com.ecoral.fiap.strategies.dados;

import com.ecoral.fiap.entities.Dados;

import java.util.List;

public interface DadosStrategy {

    List<Dados> organizar(List<Dados> dados);
}
