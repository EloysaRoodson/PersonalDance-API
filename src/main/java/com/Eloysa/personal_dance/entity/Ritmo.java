package com.Eloysa.personal_dance.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ritmo")
public class Ritmo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@ManyToMany(mappedBy = "ritmos")
	private Set<Personal> personals = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Personal> getPersonals() {
		return personals;
	}

	public void setPersonals(Set<Personal> personals) {
		this.personals = personals;
	}
}
