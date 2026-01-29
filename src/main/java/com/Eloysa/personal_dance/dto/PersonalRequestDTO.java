package com.Eloysa.personal_dance.dto;

import java.math.BigDecimal;
import java.util.Set;

public record PersonalRequestDTO(
	String nome,
	String cpf,
	String email,
	String telefone,
	Boolean isWhatsapp,
	String cidade,
	String uf,
	BigDecimal price,
	String imageUrl,
	Set<Long> ritmoIds
) {
}
