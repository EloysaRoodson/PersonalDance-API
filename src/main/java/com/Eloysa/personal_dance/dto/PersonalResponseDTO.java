package com.Eloysa.personal_dance.dto;

import java.math.BigDecimal;
import java.util.Set;

public record PersonalResponseDTO(
	Long id,
	String nome,
	String cpf,
	String email,
	String telefone,
	Boolean isWhatsapp,
	String cidade,
	String uf,
	BigDecimal price,
	Integer likes,
	String imageUrl,
	Set<String> ritmos,
	String status
) {
}
