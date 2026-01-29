package com.Eloysa.personal_dance.controller;

import com.Eloysa.personal_dance.dto.RitmoRequestDTO;
import com.Eloysa.personal_dance.dto.RitmoResponseDTO;
import com.Eloysa.personal_dance.entity.Ritmo;
import com.Eloysa.personal_dance.service.RitmoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ritmos")
public class RitmoController {

    private final RitmoService ritmoService;

    public RitmoController(RitmoService ritmoService) {
        this.ritmoService = ritmoService;
    }

    @GetMapping
    public List<RitmoResponseDTO> listAll() {
        return ritmoService.listAll().stream().map(r -> new RitmoResponseDTO(r.getId(), r.getNome())).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RitmoResponseDTO> getById(@PathVariable Long id) {
        Ritmo r = ritmoService.findById(id);
        return ResponseEntity.ok(new RitmoResponseDTO(r.getId(), r.getNome()));
    }

    @PostMapping
    public ResponseEntity<RitmoResponseDTO> create(@Valid @RequestBody RitmoRequestDTO dto) {
        Ritmo ritmo = new Ritmo();
        ritmo.setNome(dto.nome());
        Ritmo created = ritmoService.create(ritmo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RitmoResponseDTO(created.getId(), created.getNome()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RitmoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody RitmoRequestDTO dto) {
        Ritmo ritmo = new Ritmo();
        ritmo.setNome(dto.nome());
        Ritmo updated = ritmoService.update(id, ritmo);
        return ResponseEntity.ok(new RitmoResponseDTO(updated.getId(), updated.getNome()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ritmoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
