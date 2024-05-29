package com.aquapure.fiap.repositories;

import com.aquapure.fiap.entities.Dados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosRepository extends JpaRepository<Dados, Long> {
}
