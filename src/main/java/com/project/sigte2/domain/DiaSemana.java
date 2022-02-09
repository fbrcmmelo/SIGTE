package com.project.sigte2.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = ("DIASSEMANA"))
public class DiaSemana extends AbstractEntity<Long> {

	@NotBlank(message = "Campo nome vazio, insira um nome")
	@Size(min = 5, max = 13, message = "O nome do Dia da Semana deve conter entre {min} e {max} caracteres")
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	@OneToMany(mappedBy = "diaSemana")
	private List<Transporte> transportes;

	@OneToMany(mappedBy = "diaSemana")
	private List<DiasAlunosAulas> diasAlunosAula;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<DiasAlunosAulas> getDiasAlunosAula() {
		return diasAlunosAula;
	}

	public void setDiasAlunosAula(List<DiasAlunosAulas> diasAlunosAula) {
		this.diasAlunosAula = diasAlunosAula;
	}

	public List<Transporte> getTransportes() {
		return transportes;
	}

	public void setTransportes(List<Transporte> transportes) {
		this.transportes = transportes;
	}

}
