package com.Eloysa.personal_dance.dto;

import jakarta.validation.constraints.NotBlank;

public record RitmoRequestDTO(@NotBlank String nome) {
}
