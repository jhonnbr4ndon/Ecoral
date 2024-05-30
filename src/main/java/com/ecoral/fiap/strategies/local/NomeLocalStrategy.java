package com.ecoral.fiap.strategies.local;

import com.ecoral.fiap.entities.Local;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Primary
@Component
public class NomeLocalStrategy implements LocalStrategy {

    @Override
    public List<Local> organizar(List<Local> locals) {
        return locals.stream()
                .sorted(Comparator.comparing(Local::getNome))
                .collect(Collectors.toList());
    }
}
