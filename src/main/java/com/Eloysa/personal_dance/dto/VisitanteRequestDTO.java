package com.Eloysa.personal_dance.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record VisitanteRequestDTO(@NotBlank String nome, @NotBlank @Email String email) {
}
