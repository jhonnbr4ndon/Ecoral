package com.ecoral.fiap.strategies.parceiro;

import com.ecoral.fiap.entities.Parceiro;

import java.util.List;

public interface ParceiroStrategy {

    List<Parceiro> organizar(List<Parceiro> parceiros);
}
