package com.Eloysa.personal_dance.repository;

import com.Eloysa.personal_dance.entity.Personal;
import com.Eloysa.personal_dance.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepository extends JpaRepository<Personal, Long> {
    Personal findByCpf(String cpf);

    Page<Personal> findAllByStatus(Status status, Pageable pageable);
}
