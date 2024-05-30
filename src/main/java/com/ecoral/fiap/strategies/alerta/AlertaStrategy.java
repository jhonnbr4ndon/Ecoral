package com.ecoral.fiap.strategies.alerta;

import com.ecoral.fiap.entities.Alerta;

import java.util.List;

public interface AlertaStrategy {

    List<Alerta> organizar(List<Alerta> alertas);
}
