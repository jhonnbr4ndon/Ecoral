package com.aquapure.fiap.repositories;

import com.aquapure.fiap.entities.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParceirosRepository extends JpaRepository<Parceiro, Long> {
}
