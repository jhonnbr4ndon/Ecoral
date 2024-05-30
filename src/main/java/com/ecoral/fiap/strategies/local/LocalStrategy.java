package com.ecoral.fiap.strategies.local;

import com.ecoral.fiap.entities.Local;

import java.util.List;

public interface LocalStrategy {

    List<Local> organizar(List<Local> locals);
}
