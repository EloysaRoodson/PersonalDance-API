package com.Eloysa.personal_dance.service;

import com.Eloysa.personal_dance.dto.VisitanteRequestDTO;
import com.Eloysa.personal_dance.entity.Personal;
import com.Eloysa.personal_dance.entity.PersonalLike;
import com.Eloysa.personal_dance.entity.Visitante;
import com.Eloysa.personal_dance.repository.PersonalLikeRepository;
import com.Eloysa.personal_dance.repository.PersonalRepository;
import com.Eloysa.personal_dance.repository.VisitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonalLikeService {
    private final PersonalRepository personalRepository;
    private final VisitanteRepository visitanteRepository;
    private final PersonalLikeRepository personalLikeRepository;

    @Autowired
    public PersonalLikeService(PersonalRepository personalRepository, VisitanteRepository visitanteRepository, PersonalLikeRepository personalLikeRepository) {
        this.personalRepository = personalRepository;
        this.visitanteRepository = visitanteRepository;
        this.personalLikeRepository = personalLikeRepository;
    }

    @Transactional
    public void like(Long personalId, VisitanteRequestDTO dto) {
        Personal personal = personalRepository.findById(personalId)
                .orElseThrow(() -> new IllegalArgumentException("Personal não encontrado: " + personalId));

        Visitante visitante = visitanteRepository.findByEmail(dto.email()).orElseGet(() -> {
            Visitante v = new Visitante();
            v.setNome(dto.nome());
            v.setEmail(dto.email());
            return visitanteRepository.save(v);
        });

        if (personalLikeRepository.existsByVisitanteAndPersonal(visitante, personal)) {
            throw new IllegalArgumentException("Visitante já curtiu este personal");
        }

        PersonalLike pl = new PersonalLike();
        pl.setPersonal(personal);
        pl.setVisitante(visitante);
        personalLikeRepository.save(pl);

        personal.setLikes(personal.getLikes() + 1);
        personalRepository.save(personal);
    }
}
