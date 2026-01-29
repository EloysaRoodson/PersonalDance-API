package com.Eloysa.personal_dance.service;

import com.Eloysa.personal_dance.entity.Personal;
import com.Eloysa.personal_dance.entity.Status;
import com.Eloysa.personal_dance.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonalService {

	private final PersonalRepository personalRepository;

	@Autowired
	public PersonalService(PersonalRepository personalRepository) {
		this.personalRepository = personalRepository;
	}

	public Page<Personal> listAll(Pageable pageable) {
		return personalRepository.findAllByStatus(Status.ACTIVE, pageable);
	}

	public Personal findById(Long id) {
		return personalRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Personal não encontrado: " + id));
	}

	@Transactional
	public Personal create(Personal personal) {
		if (personal.getCpf() == null) throw new IllegalArgumentException("CPF é obrigatório");
		if (personalRepository.findByCpf(personal.getCpf()) != null) {
			throw new IllegalArgumentException("CPF já cadastrado");
		}
		personal.setStatus(Status.ACTIVE);
		return personalRepository.save(personal);
	}

	@Transactional
	public Personal update(Long id, Personal updated) {
		Personal existing = findById(id);
		// CPF não pode ser alterado
		if (updated.getCpf() != null && !updated.getCpf().equals(existing.getCpf())) {
			throw new IllegalArgumentException("CPF não pode ser alterado");
		}
		existing.setNome(updated.getNome());
		existing.setEmail(updated.getEmail());
		existing.setTelefone(updated.getTelefone());
		existing.setIsWhatsapp(updated.getIsWhatsapp());
		existing.setCidade(updated.getCidade());
		existing.setUf(updated.getUf());
		existing.setPrice(updated.getPrice());
		existing.setImageUrl(updated.getImageUrl());
		existing.setRitmos(updated.getRitmos());
		return personalRepository.save(existing);
	}

	@Transactional
	public void delete(Long id) {
		Personal existing = findById(id);
		existing.setStatus(Status.DELETED);
		personalRepository.save(existing);
	}
}
