package com.Eloysa.personal_dance.dto;

import java.math.BigDecimal;
import java.util.Set;
import jakarta.validation.constraints.*;

public record PersonalRequestDTO(
	@NotBlank String nome,
	@NotBlank String cpf,
	@Email String email,
	String telefone,
	Boolean isWhatsapp,
	String cidade,
	String uf,
	BigDecimal price,
	String imageUrl,
	Set<Long> ritmoIds
) {
} 
