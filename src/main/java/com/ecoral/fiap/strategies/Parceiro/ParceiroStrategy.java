package com.ecoral.fiap.strategies.Parceiro;

import com.ecoral.fiap.entities.Parceiro;

import java.util.List;

public interface ParceiroStrategy {

    List<Parceiro> organizar(List<Parceiro> parceiros);
}
