package com.Eloysa.personal_dance.service;

import com.Eloysa.personal_dance.entity.Ritmo;
import com.Eloysa.personal_dance.repository.RitmoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RitmoService {
    private final RitmoRepository ritmoRepository;

    @Autowired
    public RitmoService(RitmoRepository ritmoRepository) {
        this.ritmoRepository = ritmoRepository;
    }

    public List<Ritmo> listAll() {
        return ritmoRepository.findAll();
    }

    public Ritmo findById(Long id) {
        return ritmoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ritmo não encontrado: " + id));
    }

    @Transactional
    public Ritmo create(Ritmo ritmo) {
        return ritmoRepository.save(ritmo);
    }

    @Transactional
    public Ritmo update(Long id, Ritmo updated) {
        Ritmo existing = findById(id);
        existing.setNome(updated.getNome());
        return ritmoRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        Ritmo existing = findById(id);
        ritmoRepository.delete(existing);
    }
}
