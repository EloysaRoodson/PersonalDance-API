package com.Eloysa.personal_dance.controller;

import com.Eloysa.personal_dance.dto.PersonalRequestDTO;
import com.Eloysa.personal_dance.dto.PersonalResponseDTO;
import com.Eloysa.personal_dance.dto.VisitanteRequestDTO;
import com.Eloysa.personal_dance.entity.Personal;
import com.Eloysa.personal_dance.entity.Ritmo;
import com.Eloysa.personal_dance.repository.RitmoRepository;
import com.Eloysa.personal_dance.service.PersonalLikeService;
import com.Eloysa.personal_dance.service.PersonalService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/personals")
public class PersonalController {

	private final PersonalService personalService;
	private final RitmoRepository ritmoRepository;
	private final PersonalLikeService personalLikeService;

    public PersonalController(PersonalService personalService, RitmoRepository ritmoRepository, PersonalLikeService personalLikeService) {
        this.personalService = personalService;
        this.ritmoRepository = ritmoRepository;
        this.personalLikeService = personalLikeService;
    }

	@GetMapping
	public ResponseEntity<Page<PersonalResponseDTO>> listAll(@RequestParam(defaultValue = "0") int page,
							  @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Personal> pageResult = personalService.listAll(pageable);
		Page<PersonalResponseDTO> dtoPage = new PageImpl<>(pageResult.stream().map(this::toDto).collect(Collectors.toList()), pageable, pageResult.getTotalElements());
		return ResponseEntity.ok(dtoPage);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonalResponseDTO> getById(@PathVariable Long id) {
		Personal p = personalService.findById(id);
		return ResponseEntity.ok(toDto(p));
	}

	@PostMapping
	public ResponseEntity<PersonalResponseDTO> create(@Valid @RequestBody PersonalRequestDTO dto) {
		Personal p = fromRequest(dto);
		Personal created = personalService.create(p);
		return ResponseEntity.status(HttpStatus.CREATED).body(toDto(created));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PersonalResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PersonalRequestDTO dto) {
		Personal p = fromRequest(dto);
		Personal updated = personalService.update(id, p);
		return ResponseEntity.ok(toDto(updated));
	}

	@PostMapping("/{id}/likes")
	public ResponseEntity<Void> like(@PathVariable Long id, @Valid @RequestBody VisitanteRequestDTO dto) {
		personalLikeService.like(id, dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		personalService.delete(id);
		return ResponseEntity.noContent().build();
	}

	private PersonalResponseDTO toDto(Personal p) {
		Set<String> ritmos = p.getRitmos().stream().map(Ritmo::getNome).collect(Collectors.toSet());
		return new PersonalResponseDTO(p.getId(), p.getNome(), p.getCpf(), p.getEmail(), p.getTelefone(), p.getIsWhatsapp(), p.getCidade(), p.getUf(), p.getPrice(), p.getLikes(), p.getImageUrl(), ritmos, p.getStatus().name());
	}

	private Personal fromRequest(PersonalRequestDTO dto) {
		Personal p = new Personal();
		p.setNome(dto.nome());
		p.setCpf(dto.cpf());
		p.setEmail(dto.email());
		p.setTelefone(dto.telefone());
		p.setIsWhatsapp(dto.isWhatsapp());
		p.setCidade(dto.cidade());
		p.setUf(dto.uf());
		p.setPrice(dto.price());
		p.setImageUrl(dto.imageUrl());
		if (dto.ritmoIds() != null) {
			p.setRitmos(dto.ritmoIds().stream().map(id -> ritmoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ritmo não encontrado: " + id))).collect(Collectors.toSet()));
		}
		return p;
	}
}
