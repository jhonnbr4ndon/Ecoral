package com.ecoral.fiap.repositories;

import com.ecoral.fiap.entities.Dados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosRepository extends JpaRepository<Dados, Long> {
}
