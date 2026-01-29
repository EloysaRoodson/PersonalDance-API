package com.Eloysa.personal_dance.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "personal", indexes = {
		@Index(name = "idx_personal_cpf", columnList = "cpf", unique = true)
})
public class Personal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, unique = true)
	private String cpf;

	private String email;

	private String telefone;

	private Boolean isWhatsapp;

	private String cidade;

	private String uf;

	private BigDecimal price;

	private Integer likes = 0;

	private String imageUrl;

	@Enumerated(EnumType.STRING)
	private Status status = Status.ACTIVE;

	@ManyToMany
	@JoinTable(name = "personal_ritmo",
			joinColumns = @JoinColumn(name = "personal_id"),
			inverseJoinColumns = @JoinColumn(name = "ritmo_id"))
	private Set<Ritmo> ritmos = new HashSet<>();

	// getters and setters

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Boolean getIsWhatsapp() {
		return isWhatsapp;
	}

	public void setIsWhatsapp(Boolean isWhatsapp) {
		this.isWhatsapp = isWhatsapp;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<Ritmo> getRitmos() {
		return ritmos;
	}

	public void setRitmos(Set<Ritmo> ritmos) {
		this.ritmos = ritmos;
	}
}
