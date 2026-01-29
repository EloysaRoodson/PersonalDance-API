package com.Eloysa.personal_dance.repository;

import com.Eloysa.personal_dance.entity.Personal;
import com.Eloysa.personal_dance.entity.PersonalLike;
import com.Eloysa.personal_dance.entity.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalLikeRepository extends JpaRepository<PersonalLike, Long> {
    boolean existsByVisitanteAndPersonal(Visitante visitante, Personal personal);
}
