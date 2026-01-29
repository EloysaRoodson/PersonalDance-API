package com.Eloysa.personal_dance.repository;

import com.Eloysa.personal_dance.entity.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitanteRepository extends JpaRepository<Visitante, Long> {
    Optional<Visitante> findByEmail(String email);
}
